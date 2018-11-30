package com.learnx.demo.repository;

import com.learnx.demo.entity.Course;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Save course
     */
    public Course save(Course course) {
        int n = 0;
        if (course.getInstructorId() == null) {
            n = save(course.getTitle(), course.getDescription());
        } else {
            n = save(course.getTitle(), course.getDescription(), course.getInstructorId());
        }

        if (n == 0) {
            return null;
        }
        course.setId(RepositoryUtil.getLastInsertId(em));

        return course;
    }

    @Transactional
    protected int save(String title, String description, int instructorId) {
        String sql =
                "INSERT INTO Course (title, description, instructorId) "
                        + "VALUES (:title, :description, :instructorId)";
        Query query =
                em.createNativeQuery(sql, Course.class)
                        .setParameter("title", title)
                        .setParameter("description", description)
                        .setParameter("instructorId", instructorId);
        return query.executeUpdate();
    }

    @Transactional
    protected int save(String title, String description) {
        String sql = "INSERT INTO Course (title, description) " + "VALUES (:title, :description)";
        Query query =
                em.createNativeQuery(sql, Course.class)
                        .setParameter("title", title)
                        .setParameter("description", description);
        return query.executeUpdate();
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM Course";
        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public List<Course> findCourseByStudentId(int studentId) {
        String sql =
                "SELECT C.* " +
                        "FROM Course C INNER JOIN Enroll E ON C.id = E.courseId " +
                        "WHERE E.studentId = :studentId";
        Query query = em.createNativeQuery(sql, Course.class)
                .setParameter("studentId", studentId);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public List<Course> findByTitle(String title) {
        String sql = "select * from Course where title = :title";
        Query query = em.createNativeQuery(sql, Course.class);
        query.setParameter("title", title);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

/*
SQL Query
(
select *
from Course
where match (title,description) against ('data' in natural language mode with query expansion)
order by (
	match (title) against ('data' in natural language mode with query expansion) * 10
	+
	match (description) against ('data' in natural language mode with query expansion)
)
desc
limit 10
)
UNION
(
	select * from Course
	where instructorId in (
		select id from AppUser
        where username like '%data%'
	)
);
 */
    public List<Course> search(String keyword) {
        List<String> queryList = new ArrayList<>();
        queryList.add(String.format("select * from Course where instructorId in (select id from AppUser where username like '%%%s%%') order by instructorId limit 10", keyword));
        queryList.add(String.format("select * from Course where match (title,description) against ('%s' in natural language mode with query expansion) order by ((match (title) against ('%s' in natural language mode with query expansion) * 10) + (match (description) against ('%s' in natural language mode with query expansion))) desc limit 10", keyword, keyword, keyword));
        queryList.add(String.format("select * from Course where title like '%%%s%%'", keyword));
        queryList.add(String.format("select * from Course where description like '%%%s%%'", keyword));
        String sql = RepositoryUtil.unionQuery(queryList);
        Query query = em.createNativeQuery(sql, Course.class);
        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public boolean exists(int courseId) {
        return findById(courseId) != null;
    }

    public Course findById(int courseId) {
        String sql = "SELECT * FROM course " + "WHERE id = :courseId";

        Query query = em.createNativeQuery(sql, Course.class)
                .setParameter("courseId", courseId);

        return RepositoryUtil.findOneResult(query.getResultList(), Course.class);
    }
}
