package com.learnx.demo.repository;

import com.learnx.demo.entity.Discussion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussionRepositoryTest {

    @Autowired
    private DiscussionRepository repository;

    @Test
    public void findByCourseId() {
        List<Discussion> results = repository.findByCourseId(1);

        results.forEach(System.out::println);
    }
}