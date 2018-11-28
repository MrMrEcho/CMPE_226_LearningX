package com.learnx.demo.service;

import com.learnx.demo.model.Homework;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Override
    public List<Homework> listHomeworkdsByCourseId(int courseId) {
        return null;
    }

    @Override
    public Homework create(Homework newHomework) {
        return null;
    }
}
