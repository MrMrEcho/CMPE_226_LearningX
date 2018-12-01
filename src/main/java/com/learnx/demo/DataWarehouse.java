package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.Course;
import com.learnx.demo.entity.Discussion;
import com.learnx.demo.entity.Homework;
import com.learnx.demo.repository.AppUserRepository;
import com.learnx.demo.repository.CourseRepository;
import com.learnx.demo.repository.DiscussionRepository;
import com.learnx.demo.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Random;

@Component
public class DataWarehouse {

    private static final int ADMIN_NUMBER = 1;
    private static final int INSTITUTE_NUMBER = 2;
    private static final int INSTRUCTOR_NUMBER_PER_INSTITUTE = 2;
    private static final int STUDENT_NUMBER_PER_INSTRUCTOR = 2;
    private static final int INSTITUTE_OFFSET = ADMIN_NUMBER;
    private static final int INSTRUCTOR_OFFSET = ADMIN_NUMBER + INSTITUTE_NUMBER;
    private static final int STUDENT_OFFSET = INSTRUCTOR_OFFSET + ADMIN_NUMBER * INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE;
    private static final int STUDENT_NUMBER = INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE * STUDENT_NUMBER_PER_INSTRUCTOR;

    private static final int COURSE_NUMBER = 8;
    private static final int MATERIAL_PER_COURSE = 2;
    private static final int DISCUSSION_PER_COURSE = 2;

    private static final Random RANDOM = new Random();

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    DiscussionRepository discussionRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    DataSource dataSource;
    
    @Autowired
    private EntityManager em;

    @Transactional
    public void generate() {
        generateUsers();
        generateWorkFor();
        generateCourses();
        generateHomeworks();   
        generateMaterials();
        generateEnrolls();
        generateDiscussions();
    }

    private void generateEnrolls() {
        enrollCourse(8, 1);
        rateCourse(8, 1);
        enrollCourse(8, 2);
        rateCourse(8, 2);
        enrollCourse(9, 1);
        rateCourse(9, 1);
        enrollCourse(9, 2);
        rateCourse(9, 2);
        enrollCourse(10, 3);
        rateCourse(10, 3);
        enrollCourse(10, 4);
        rateCourse(10, 4);
        enrollCourse(11, 3);
        rateCourse(11, 3);
        enrollCourse(11, 4);
        rateCourse(11, 4);
        enrollCourse(12, 5);
        rateCourse(12, 5);
        enrollCourse(12, 6);
        rateCourse(12, 6);
        enrollCourse(13, 5);
        rateCourse(13, 5);
        enrollCourse(13, 6);
        rateCourse(13, 6);
        enrollCourse(14, 7);
        rateCourse(14, 7);
        enrollCourse(14, 8);
        rateCourse(14, 8);
        enrollCourse(15, 7);
        rateCourse(15, 7);
        enrollCourse(15, 8);
        rateCourse(15, 8);
    }

    private void rateCourse(int studentId, int courseId) {
        String sql =
                "INSERT INTO Rating (studentId, courseId, rate) "
                        + "VALUES (:studentId, :courseId, :rate)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("studentId", studentId)
                        .setParameter("courseId", courseId)
                        .setParameter("rate", RANDOM.nextInt(10));
        query.executeUpdate();
    }

    private void enrollCourse(int studentId, int courseId) {
        String sql =
                "INSERT INTO Enroll (studentId, courseId) "
                        + "VALUES (:studentId, :courseId)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("studentId", studentId)
                        .setParameter("courseId", courseId);
        query.executeUpdate();
    }

    private void generateDiscussions() {
        for (int i = 1; i <= STUDENT_NUMBER; i++) {
            int studentId = STUDENT_OFFSET + i;
            for (Course course : courseRepository.findCourseByStudentId(studentId)) {
                discussionRepository.save(new Discussion(null, "Question from student " + studentId, "Question about course " + course.getId(), studentId, course.getId()));
                discussionRepository.save(new Discussion(null, "Answer to student " + studentId, "Answer from instructor " + course.getInstructorId(), course.getInstructorId(), course.getId()));
            }
        }
    }

    private void generateMaterials() {
        for (int i = 0; i < COURSE_NUMBER; i++) {
            for (int j = 0; j < MATERIAL_PER_COURSE; j++) {
                generateMaterials(i + 1, j + 1);
            }
        }
    }

    private void generateMaterials(int courseId, int materialNo) {
        String sql =
                "INSERT INTO Material (courseId, title, url) "
                        + "VALUES (:courseId, :title, :url)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("courseId", courseId)
                        .setParameter("title", "title " + materialNo)
                        .setParameter("url", "url " + materialNo);
        query.executeUpdate();
    }

    private void generateHomeworks() {
        for (int i = 0; i < COURSE_NUMBER; i++) {
            generateHomeworks(i + 1);
        }
    }

    private void generateHomeworks(int courseId) {
        Homework homework = new Homework();
        homework.setTitle("Course " + courseId + ": homework 1");
        homework.setCourseId(courseId);
        homework.setType(0);
        homework.setContent("Content 1");
        homeworkRepository.save(homework);

        homework.setTitle("Course " + courseId + ": homework 2");
        homework.setContent("Content 2");
        homeworkRepository.save(homework);

        homework.setTitle("Course " + courseId + ": Exam");
        homework.setType(1);
        homework.setContent("Content exam");
        homeworkRepository.save(homework);
    }

    private void generateCourses() {
        loadSql("import-courses.sql");
    }

    private void loadSql(String sqlFile) {
        Resource initData = new ClassPathResource(sqlFile);
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    protected void generateWorkFor() {
        for(int i = 0; i < INSTITUTE_NUMBER; i++){
            for(int j = 1; j <= INSTRUCTOR_NUMBER_PER_INSTITUTE; j++){
                workFor(i * INSTRUCTOR_NUMBER_PER_INSTITUTE + j, i + 1);
            }
        }
    }

    private void workFor(int instructorId, int instituteId) {
        instructorId += INSTRUCTOR_OFFSET;
        instituteId += INSTITUTE_OFFSET;
        String sql =
                "INSERT INTO WorkFor (instructorId, instituteId) "
                        + "VALUES (:instructorId, :instituteId)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("instructorId", instructorId)
                        .setParameter("instituteId", instituteId);
        query.executeUpdate();
    }

    private void generateUsers() {
        generateUsers("admin", AppUser.ADMIN, ADMIN_NUMBER);
        generateUsers("institute", AppUser.INSTITUTE, INSTITUTE_NUMBER);
        generateUsers("instructor", AppUser.INSTRUCTOR, INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE);
        generateUsers("student", AppUser.STUDENT, STUDENT_NUMBER);
    }

    private void generateUsers(String username, int role, int count) {
        for (int i = 1; i <= count; i++) {
            userRepository.save(new AppUser(username + i, "123", role));
        }
    }


}
