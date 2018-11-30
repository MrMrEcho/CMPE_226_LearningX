package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private CourseService service;

    @Test
    public void listCourses() {
        List<CourseDto> results = service.listCourses();

        results.forEach(System.out::println);
    }

    @Test
    public void listCourseByStudentId() {
        List<CourseDto> results = service.listCourseByStudentId(2);

        results.forEach(System.out::println);
    }
}
