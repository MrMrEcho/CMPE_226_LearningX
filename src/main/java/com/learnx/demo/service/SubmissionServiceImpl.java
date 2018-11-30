package com.learnx.demo.service;

import static com.learnx.demo.entity.Homework.Type.EXAM;

import com.learnx.demo.entity.Submission;
import com.learnx.demo.model.SubmissionDto;
import com.learnx.demo.repository.RepositoryUtil;
import com.learnx.demo.repository.SubmissionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// FIXME: Current implementation does not include input check let's fix when it comes out

@Service
public class SubmissionServiceImpl implements SubmissionService {

    SubmissionRepository repository;

    public SubmissionServiceImpl(SubmissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubmissionDto> listExamSubmissionByCourseId(int courseId) {
        return RepositoryUtil.mapAll(
                repository.findSubmissionByCourseId(courseId, EXAM), SubmissionServiceImpl::toDto);
    }

    @Override
    public List<SubmissionDto> listExamSubmissionByCourseId(int courseId, boolean hasGrade) {
        return RepositoryUtil.mapAll(
                repository.findSubmissionByCourseId(courseId, hasGrade, EXAM),
                SubmissionServiceImpl::toDto);
    }

    @Override
    public SubmissionDto findById(int studentId, int homeworkId) {
        return toDto(repository.findById(studentId, homeworkId));
    }

    @Override
    public SubmissionDto create(SubmissionDto dto) {
        // TODO: need to check student is enroll is course
        Submission newEntity = toEntity(dto);
        Submission saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    private static Submission toEntity(SubmissionDto dto) {
        Submission entity = new Submission();
        entity.setStudentId(dto.getStudentId());
        entity.setHomeworkId(dto.getHomeworkId());
        entity.setAnswer(dto.getAnswer());
        entity.setGrade(dto.getGrade());
        entity.setHasGrade(dto.isHasGrade());

        return entity;
    }

    @Override
    public SubmissionDto update(SubmissionDto newDto) {
        // Check difference between update
        Submission newEntity = repository.update(toEntity(newDto));

        return toDto(newEntity);
    }

    private static SubmissionDto toDto(Submission entity) {
        SubmissionDto dto = new SubmissionDto();
        dto.setStudentId(entity.getStudentId());
        dto.setHomeworkId(entity.getHomeworkId());
        dto.setAnswer(entity.getAnswer());
        dto.setGrade(entity.getGrade());
        dto.setHasGrade(entity.isHasGrade());

        return dto;
    }
}
