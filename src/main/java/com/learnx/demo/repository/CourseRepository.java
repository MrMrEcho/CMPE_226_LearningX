package com.learnx.demo.repository;

import com.learnx.demo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CourseRepository {

    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Save course
     *
     * @param course
     * @return
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
        String sql = "INSERT INTO Course (title, description, instructorId) " +
                "VALUES (:title, :description, :instructorId)";
        Query query = em.createNativeQuery(sql, Course.class).
                setParameter("title", title).
                setParameter("description", description).
                setParameter("instructorId", instructorId);
        return query.executeUpdate();
    }

    @Transactional
    protected int save(String title, String description) {
        String sql = "INSERT INTO Course (title, description) " +
                "VALUES (:title, :description)";
        Query query = em.createNativeQuery(sql, Course.class).
                setParameter("title", title).
                setParameter("description", description);
        return query.executeUpdate();
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM Course";
        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public Course findById(int courseId) {
        String sql = "SELECT * FROM course " +
                "WHERE id = :courseId";

        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.findOneResult(query.getResultList(), Course.class);
    }

    public List<Course> findByTitle(String title){
        String sql = "select * from Course where title = :title";
        Query query = em.createNativeQuery(sql, Course.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Course> search(String keyword) {
        String sql = "select * from Course where match (title,description) against (:keyword with query expansion)";
        Query query = em.createNativeQuery(sql, Course.class);
        query.setParameter("keyword", keyword);
        return query.getResultList();
    }
}
