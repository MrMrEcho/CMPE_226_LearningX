package com.learnx.demo.service;

import com.learnx.demo.model.Homework;

import java.util.List;

public interface HomeworkService {

    List<Homework> listHomeworkdsByCourseId(int courseId);

    Homework create(Homework newHomework);
}
