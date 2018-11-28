package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> searchCourses(String keyword);
    List<Course> listCourses();
    List<Course> listCoursesSortedByRating(boolean ascending);
    List<Course> listCoursesByUserId(int userId, Boolean complete);
    List<Course> listCoursesByInstructorId(int instructorId);
    List<Course> listCoursesByInstituteId(int instructorId);

    Course getCourseById(int courseId);

    // CRUD
    Course create(Course newCourse);
    Course update(Course course);

    // User Course Operation
    // TODO: Rename or move to UserService, UserCourseService
    boolean isEnrolled(int courseId, int userId);
    boolean isComplete(int courseId, int userId);

    // TODO: change parameter order for naming rule consistency
    Course enroll(AppUserDto user, Course course);
    Course drop(AppUserDto user, Course course);
}
