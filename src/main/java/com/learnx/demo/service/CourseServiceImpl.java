package com.learnx.demo.service;

import com.learnx.demo.model.AppUser;
import com.learnx.demo.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> searchCourses(String keyword) {
        return null;
    }

    @Override
    public List<Course> listCourses() {
        return null;
    }

    @Override
    public List<Course> listCoursesSortedByRating(boolean ascending) {
        return null;
    }

    @Override
    public List<Course> listCoursesByUserId(long userId, Boolean complete) {
        return null;
    }

    @Override
    public List<Course> listCoursesByInstructorId(long instructorId) {
        return null;
    }

    @Override
    public List<Course> listCoursesByInstituteId(long instructorId) {
        return null;
    }

    @Override
    public Course getCourseById(long courseId) {
        return null;
    }

    @Override
    public Course create(Course newCourse) {
        return null;
    }

    @Override
    public Course update(Course course) {
        return null;
    }

    @Override
    public boolean isEnrolled(long courseId, long userId) {
        return false;
    }

    @Override
    public boolean isComplete(long courseId, long userId) {
        return false;
    }

    @Override
    public Course enroll(AppUser user, Course course) {
        return null;
    }

    @Override
    public Course drop(AppUser user, Course course) {
        return null;
    }
}
