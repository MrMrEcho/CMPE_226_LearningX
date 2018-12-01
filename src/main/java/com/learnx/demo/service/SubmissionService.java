package com.learnx.demo.service;

import com.learnx.demo.model.SubmissionDto;
import java.util.List;

public interface SubmissionService {

    List<SubmissionDto> listExamSubmissionByCourseId(int courseId);

    List<SubmissionDto> listExamSubmissionByCourseId(int courseId, boolean hasGrade);

    boolean exist(int studentId, int homeworkId);

    SubmissionDto findById(int studentId, int homeworkId);

    SubmissionDto create(SubmissionDto newSubmission);

    // Student submit and instructor grade all the same thing for insert operation
    SubmissionDto update(SubmissionDto submissionDto);
}
