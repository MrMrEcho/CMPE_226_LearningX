package com.learnx.demo.service;

import com.learnx.demo.model.Submission;

import java.util.List;

public interface SubmissionService {

    List<Submission> listSubmissionByHomeworkId(Submission newSubmission);

    Submission create(Submission newSubmission);
    Submission grade(Submission submission);
}
