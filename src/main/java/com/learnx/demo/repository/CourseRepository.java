package com.learnx.demo.repository;

import com.learnx.demo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CourseRepository {

    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    public List<Course> findAll() {
        String sql = "select * from course";

        Query query = em.createNativeQuery(sql, Course.class);

        return RepositoryUtil.toList(query.getResultList(), Course.class);
    }
}
