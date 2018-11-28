package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;
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
    public List<Course> listCoursesByUserId(int userId, Boolean complete) {
        return null;
    }

    @Override
    public List<Course> listCoursesByInstructorId(int instructorId) {
        return null;
    }

    @Override
    public List<Course> listCoursesByInstituteId(int instructorId) {
        return null;
    }

    @Override
    public Course getCourseById(int courseId) {
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
    public boolean isEnrolled(int courseId, int userId) {
        return false;
    }

    @Override
    public boolean isComplete(int courseId, int userId) {
        return false;
    }

    @Override
    public Course enroll(AppUserDto user, Course course) {
        return null;
    }

    @Override
    public Course drop(AppUserDto user, Course course) {
        return null;
    }
}
