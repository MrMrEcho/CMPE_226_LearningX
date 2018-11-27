package com.learnx.demo.service;

import com.learnx.demo.model.Submission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Override
    public List<Submission> listSubmissionByHomeworkId(Submission newSubmission) {
        return null;
    }

    @Override
    public Submission create(Submission newSubmission) {
        return null;
    }

    @Override
    public Submission grade(Submission submission) {
        return null;
    }
}
