package com.learnx.demo.service;

import com.learnx.demo.model.CourseDto;
import com.learnx.demo.model.SeriesDto;
import java.util.List;

public interface SeriesService {

    List<CourseDto> listCoursesBySeriesId(int seriesId);

    List<SeriesDto> listSeries();

    List<SeriesDto> listSeriesByInstituteId(int instituteId);

    List<SeriesDto> listSeriesByStudentId(int studentId);

    SeriesDto create(SeriesDto seriesDto);

    SeriesDto udpate(SeriesDto newSeriesDto);

    SeriesDto addCourseBySeriesId(int courseId, int seriesId);
}
