package com.learnx.demo.service;

import com.learnx.demo.model.SeriesDto;
import java.util.List;

public interface SeriesService {

    List<SeriesDto> listSeries();

    List<SeriesDto> listSeriesByInstituteId(int instituteId);

    List<SeriesDto> listSeriesByStudentId(int studentId);

    SeriesDto create(SeriesDto seriesDto);

    SeriesDto update(SeriesDto newSeriesDto);

    void addCourseBySeriesId(int courseId, int seriesId);

    SeriesDto getSeriesById(int seriesId);
}
