package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> searchCourses(String keyword);

    List<CourseDto> listCourses();

    List<CourseDto> listCoursesSortedByRating(boolean ascending);

    List<CourseDto> listCoursesByUserId(int userId);

    List<CourseDto> listOnGoingCoursesByUserId(int userId);

    List<CourseDto> listFinishedCoursesByUserId(int userId);

    List<CourseDto> listCoursesByInstructorId(int instructorId);

    List<CourseDto> listCoursesByInstituteId(int instructorId);

    CourseDto getCourseById(int courseId);

    /**
     * Create a course record from
     * @param course
     * @return
     */
    CourseDto create(CourseDto course);

    CourseDto update(CourseDto newCourse);

}
