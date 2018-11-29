package com.learnx.demo.repository;

import com.learnx.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CourseRepository repository;

    @Test()
    public void testFindAll() {
        List<Course> results = repository.findAll();

        results.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void findById() {
        Course course = new Course("CMPE226", "Database Design");
        em.persist(course);

        Course result = repository.findById(course.getId());
        assertEquals(result, course);
    }
}