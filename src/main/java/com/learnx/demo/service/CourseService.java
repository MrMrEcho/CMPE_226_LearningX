package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;
import java.util.List;

public interface CourseService {

    List<CourseDto> searchCourses(String keyword);

    List<CourseDto> listCourses();

    List<CourseDto> listCoursesSortedByRating(boolean ascending);

    List<CourseDto> listCourseByStudentId(int studentId);

    List<CourseDto> listOnGoingCoursesByStudentId(int studentId);

    List<CourseDto> listFinishedCoursesByStudentId(int studentId);

    List<CourseDto> listCoursesByInstructorId(int instructorId);

    List<CourseDto> listCoursesByInstituteId(int InstituteId);

    List<CourseDto> listCoursesBySeriesId(int seriesId);

    CourseDto getCourseById(int courseId);

    CourseDto create(CourseDto course);

    CourseDto update(CourseDto newCourse);

}
