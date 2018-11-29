package com.learnx.demo.repository;

import com.learnx.demo.entity.Homework;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkRepositoryTest {

    @Autowired
    private HomeworkRepository repository;

    @Test
    public void findByCourseId() {
        List<Homework> results = repository.findByCourseId(1);

        results.forEach(System.out::println);
    }
}