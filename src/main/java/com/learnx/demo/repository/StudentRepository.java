package com.learnx.demo.repository;

import com.learnx.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentRepository {

    private final EntityManager em;

    @Autowired
    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    List<Student> findAll() {
        String sql = "select * from Student;";

        Query query = em.createNativeQuery(sql, Student.class);
        return RepositoryUtil.toList(query.getResultList(), Student.class);
    }
}
