package com.learnx.demo.service;

import com.learnx.demo.model.Course;
import com.learnx.demo.model.Series;

import java.util.List;

// TODO: Rename to CourseSeriesService ?
public interface SeriesService {

    List<Course> listCoursesBySeriesId(long seriedId);
    List<Series> listSeries();
    List<Series> listSeriesByInstituteId(long instituteId);
    List<Series> listSeriesByStudentId(long studentId);

    // CRUD
    Series create(Series newSeries);

    Series addCourseBySeriesId(Course course, long seriesId);
}
