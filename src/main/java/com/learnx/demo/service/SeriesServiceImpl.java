package com.learnx.demo.service;

import com.learnx.demo.model.Course;
import com.learnx.demo.model.Series;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Override
    public List<Course> listCoursesBySeriesId(int seriedId) {
        return null;
    }

    @Override
    public List<Series> listSeries() {
        return null;
    }

    @Override
    public List<Series> listSeriesByInstituteId(int instituteId) {
        return null;
    }

    @Override
    public List<Series> listSeriesByStudentId(int studentId) {
        return null;
    }

    @Override
    public Series create(Series newSeries) {
        return null;
    }

    @Override
    public Series addCourseBySeriesId(int courseId, int seriesId) {
        return null;
    }
}
