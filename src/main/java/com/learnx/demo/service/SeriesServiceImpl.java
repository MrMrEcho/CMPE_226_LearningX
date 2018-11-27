package com.learnx.demo.service;

import com.learnx.demo.model.Course;
import com.learnx.demo.model.Series;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Override
    public List<Course> listCoursesBySeriesId(long seriedId) {
        return null;
    }

    @Override
    public List<Series> listSeries() {
        return null;
    }

    @Override
    public List<Series> listSeriesByInstituteId(long instituteId) {
        return null;
    }

    @Override
    public Series create(Series newSeries) {
        return null;
    }

    @Override
    public Series addCourseBySeriesId(Course course, long seriesId) {
        return null;
    }
}
