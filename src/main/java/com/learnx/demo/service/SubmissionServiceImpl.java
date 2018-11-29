package com.learnx.demo.service;

import com.learnx.demo.model.SubmissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Override
    public List<SubmissionDto> listSubmissionByHomeworkId(SubmissionDto newSubmission) {
        return null;
    }

    @Override
    public SubmissionDto create(SubmissionDto newSubmission) {
        return null;
    }

    @Override
    public SubmissionDto grade(SubmissionDto submission) {
        return null;
    }
}
