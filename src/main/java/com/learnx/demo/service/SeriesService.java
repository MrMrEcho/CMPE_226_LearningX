package com.learnx.demo.service;

import com.learnx.demo.model.Course;
import com.learnx.demo.model.Series;

import java.util.List;

// TODO: Rename to CourseSeriesService ?
public interface SeriesService {

    List<Course> listCoursesBySeriesId(int seriedId);
    List<Series> listSeries();
    List<Series> listSeriesByInstituteId(int instituteId);
    List<Series> listSeriesByStudentId(int studentId);

    // CRUD
    Series create(Series newSeries);

    Series addCourseBySeriesId(int courseId, int seriesId);
}
