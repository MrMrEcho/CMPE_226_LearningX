package com.learnx.demo.service;

import com.learnx.demo.model.DiscussionDto;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussionServiceImplTest {

    @Autowired
    private DiscussionService service;

    @Test
    public void listDiscussionsByCourseId() {
        List<DiscussionDto> results = service.listDiscussionsByCourseId(1);

        results.forEach(System.out::println);
    }

    @Test
    public void create() {
    }
}