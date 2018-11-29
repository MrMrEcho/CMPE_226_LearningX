package com.learnx.demo.service;

import com.learnx.demo.model.HomeworkDto;

import java.util.List;

public interface HomeworkService {
    List<HomeworkDto> listHomeworksByCourseId(int courseId);

    HomeworkDto create(HomeworkDto homework);

    HomeworkDto update(HomeworkDto newHomework);
}
