package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.Homework;
import com.learnx.demo.repository.AppUserRepository;
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

@Component
public class DataWarehouse {

    private static final int ADMIN_NUMBER = 1;
    private static final int INSTITUTE_NUMBER = 2;
    private static final int INSTRUCTOR_NUMBER_PER_INSTITUTE = 2;
    private static final int STUDENT_NUMBER_PER_INSTRUCTOR = 2;
    private static final int INSTITUTE_OFFSET = ADMIN_NUMBER;
    private static final int INSTRUCTOR_OFFSET = ADMIN_NUMBER + INSTITUTE_NUMBER;
    private static final int STUDENT_OFFSET = INSTRUCTOR_OFFSET + ADMIN_NUMBER * INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE;

    public static final int COURSE_NUMBER = 8;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    HomeworkRepository homeworkRepository;

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
        generateUsers("student", AppUser.STUDENT, INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE * STUDENT_NUMBER_PER_INSTRUCTOR);
    }

    private void generateUsers(String username, int role, int count) {
        for (int i = 1; i <= count; i++) {
            userRepository.save(new AppUser(username + i, "123", role));
        }
    }


}
