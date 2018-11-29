package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;
import com.learnx.demo.model.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Override
    public List<CourseDto> listCoursesBySeriesId(int seriesId) {
        return null;
    }

    @Override
    public List<SeriesDto> listSeries() {
        return null;
    }

    @Override
    public List<SeriesDto> listSeriesByInstituteId(int instituteId) {
        return null;
    }

    @Override
    public List<SeriesDto> listSeriesByStudentId(int studentId) {
        return null;
    }

    @Override
    public SeriesDto create(SeriesDto seriesDto) {
        return null;
    }

    @Override
    public SeriesDto udpate(SeriesDto newSeriesDto) {
        return null;
    }

    @Override
    public SeriesDto addCourseBySeriesId(int courseId, int seriesId) {
        return null;
    }
}
