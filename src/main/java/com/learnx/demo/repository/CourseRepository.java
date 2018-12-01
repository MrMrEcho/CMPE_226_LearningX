package com.learnx.demo.repository;

import com.learnx.demo.entity.Course;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private final EntityManager em;

    @Autowired
    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Course save(Course entity) {
        String sql = "INSERT INTO Course (title, description, instructorId) "
                + "VALUES (:title, :description, :instructorId)";
        Query query =
                em.createNativeQuery(sql, Course.class)
                        .setParameter("title", entity.getTitle())
                        .setParameter("description", entity.getDescription())
                        .setParameter("instructorId", entity.getInstructorId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Course saveEntity = new Course();
        saveEntity.setId(RepositoryUtil.getLastInsertId(em));
        saveEntity.setTitle(entity.getTitle());
        saveEntity.setDescription(entity.getDescription());
        saveEntity.setInstructorId(entity.getInstructorId());

        return saveEntity;
    }

    @Transactional
    public Course update(Course newEntity) {
        String sql = "UPDATE Course " +
                "SET title = :title, description = :description, instructorId = :instructorId " +
                "WHERE id = :id";
        Query query = em.createNativeQuery(sql)
                .setParameter("title", newEntity.getTitle())
                .setParameter("description", newEntity.getDescription())
                .setParameter("instructorId", newEntity.getInstructorId())
                .setParameter("id", newEntity.getId());

        if(query.executeUpdate() == 0) {
            return null;
        }

        return newEntity;
    }


    public List<Course> findAll() {
        String sql = "SELECT * FROM Course";
        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public List<Course> findCourseByStudentId(int studentId) {
        String sql = "SELECT C.* " +
                "FROM Course C INNER JOIN Enroll E ON C.id = E.courseId " +
                "WHERE E.studentId = :studentId";
        Query query = em.createNativeQuery(sql, Course.class)
                .setParameter("studentId", studentId);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }

    public List<Course> findByTitle(String title) {
        String sql = "SELECT * FROM Course WHERE title = :title";
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
        queryList.add(String
                .format("select * from Course where instructorId in (select id from AppUser where username like '%%%s%%') order by instructorId limit 10",
                        keyword));
        queryList.add(String
                .format("select * from Course where match (title,description) against ('%s' in natural language mode with query expansion) order by ((match (title) against ('%s' in natural language mode with query expansion) * 10) + (match (description) against ('%s' in natural language mode with query expansion))) desc limit 10",
                        keyword, keyword, keyword));
        queryList.add(String.format("select * from Course where title like '%%%s%%'", keyword));
        queryList.add(String
                .format("select * from Course where description like '%%%s%%'", keyword));
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

    public List<Course> findCourseOrderByRating(boolean ascending) {
        String sql = "SELECT C.* " +
                "FROM Course C INNER JOIN AverageRating R ON C.id = R.id " +
                "ORDER BY R.rate ";
        sql = sql + (ascending ? "ASC" : "DESC");
        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.castAll(query.getResultList(), Course.class);
    }
}
