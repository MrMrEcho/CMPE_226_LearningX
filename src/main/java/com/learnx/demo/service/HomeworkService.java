package com.learnx.demo.service;

import com.learnx.demo.model.HomeworkDto;
import java.util.List;

public interface HomeworkService {

    List<HomeworkDto> listHomeworksByCourseId(int courseId);

    List<HomeworkDto> listHomeworksByCourseIdByType(int courseId, HomeworkDto.Type type);

    List<HomeworkDto> listExamsByCourseId(int courseId);

    List<HomeworkDto> listPracticesByCourseId(int courseId);

    HomeworkDto create(HomeworkDto homework);

    HomeworkDto update(HomeworkDto newHomework);
}
