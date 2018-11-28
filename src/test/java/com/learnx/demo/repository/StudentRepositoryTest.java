package com.learnx.demo.repository;

import com.learnx.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {


    @Autowired
    private StudentRepository repository;

    @Test
    public void testFindAll() {
        List<Student> results = repository.findAll();

        results.forEach(System.out::println);
    }
}