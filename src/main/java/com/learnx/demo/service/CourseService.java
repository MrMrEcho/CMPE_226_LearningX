package com.learnx.demo.service;

import com.learnx.demo.model.AppUser;
import com.learnx.demo.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> searchCourses(String keyword);
    List<Course> listCourses();
    List<Course> listCoursesSortedByRating(boolean ascending);
    List<Course> listCoursesByUserId(long userId, Boolean complete);
    List<Course> listCoursesByInstructorId(long instructorId);
    List<Course> listCoursesByInstituteId(long instructorId);

    Course getCourseById(long courseId);

    // CRUD
    Course create(Course newCourse);
    Course update(Course course);

    // User Course Operation
    // TODO: Rename or move to UserService, UserCourseService
    boolean isEnrolled(long courseId, long userId);
    boolean isComplete(long courseId, long userId);

    // TODO: change parameter order for naming rule consistency
    Course enroll(AppUser user, Course course);
    Course drop(AppUser user, Course course);
}
