package com.learnx.demo.service;

import com.learnx.demo.model.SubmissionDto;
import java.util.List;

public interface SubmissionService {

    List<SubmissionDto> listSubmissionByHomeworkId(SubmissionDto newSubmission);

    SubmissionDto create(SubmissionDto newSubmission);

    SubmissionDto grade(SubmissionDto submission);
}
