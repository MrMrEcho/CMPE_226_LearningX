package com.learnx.demo.service;

import com.learnx.demo.model.SubmissionDto;
import java.util.List;
import org.springframework.stereotype.Service;

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
