package com.learnx.demo.repository;

import static org.junit.Assert.assertEquals;

import com.learnx.demo.entity.Discussion;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void save() {
        Discussion entity = new Discussion();
        entity.setUserId(4);
        entity.setCourseId(1);
        entity.setTitle("dummy title");

        Discussion result = repository.save(entity);

        assertEquals(result, entity);
    }
}