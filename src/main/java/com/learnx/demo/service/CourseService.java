package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> searchCourses(String keyword);
    List<CourseDto> listCourses();
    List<CourseDto> listCoursesSortedByRating(boolean ascending);
    List<CourseDto> listCoursesByUserId(int userId, Boolean complete);
    List<CourseDto> listCoursesByInstructorId(int instructorId);
    List<CourseDto> listCoursesByInstituteId(int instructorId);

    CourseDto getCourseById(int courseId);

    CourseDto create(CourseDto course);
    CourseDto update(CourseDto newCourse);

    // TODO: Rename or move to UserService, UserCourseService
    boolean isEnrolled(int courseId, int userId);
    boolean isComplete(int courseId, int userId);

    // TODO: change parameter order for naming rule consistency
    CourseDto enroll(int studentId, int courseId);
    CourseDto drop(int studentId, int courseId);
}
