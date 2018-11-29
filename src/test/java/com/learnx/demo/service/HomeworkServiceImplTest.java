package com.learnx.demo.service;

import com.learnx.demo.model.HomeworkDto;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkServiceImplTest {

    @Autowired
    private HomeworkService service;

    @Test
    public void listHomeworksByCourseId() {
        List<HomeworkDto> results = service.listHomeworksByCourseId(1);

        results.forEach(System.out::println);
    }

    @Test
    public void listExamsByCourseId() {
        List<HomeworkDto> results = service.listExamsByCourseId(1);

        results.forEach(System.out::println);
    }

    @Test
    public void listPracticesByCourseId() {
        List<HomeworkDto> results = service.listPracticesByCourseId(1);

        results.forEach(System.out::println);
    }
}