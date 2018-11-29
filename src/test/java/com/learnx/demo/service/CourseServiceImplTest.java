package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void listCoursesSortedByRating() {
    }

    @Test
    public void listCoursesByUserId() {
    }

    @Test
    public void listCoursesByInstructorId() {
    }

    @Test
    public void listCoursesByInstituteId() {
    }

    @Test
    public void getCourseById() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void isEnrolled() {
    }

    @Test
    public void isComplete() {
    }

    @Test
    public void enroll() {
    }

    @Test
    public void drop() {
    }
}