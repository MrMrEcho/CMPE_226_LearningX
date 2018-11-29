package com.learnx.demo.service;

import com.learnx.demo.entity.Homework;
import com.learnx.demo.model.HomeworkDto;
import com.learnx.demo.repository.CourseRepository;
import com.learnx.demo.repository.HomeworkRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final CourseRepository courseRepository;
    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(CourseRepository courseRepository,
            HomeworkRepository homeworkRepository) {
        this.courseRepository = courseRepository;
        this.homeworkRepository = homeworkRepository;
    }

    private static Homework toEntity(HomeworkDto dto) {
        Homework entity = new Homework();
        entity.setId(dto.getId());
        entity.setCourseId(dto.getCourseId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setType(dto.getType().getValue());

        return entity;
    }    private static HomeworkDto toDto(Homework entity) {
        HomeworkDto dto = new HomeworkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setType(HomeworkDto.Type.getEnum(entity.getType()));

        return dto;
    }



    @Override
    public List<HomeworkDto> listHomeworksByCourseId(int courseId) {
        // TODO: Check courseId exist
        return RepositoryUtil
                .mapAll(homeworkRepository.findByCourseId(courseId), HomeworkServiceImpl::toDto);
    }

    @Override
    public List<HomeworkDto> listHomeworksByCourseIdByType(int courseId, HomeworkDto.Type type) {
        return listHomeworksByCourseId(courseId).stream().
                filter(e -> e.getType() == type).collect(Collectors.toList());
    }

    @Override
    public List<HomeworkDto> listExamsByCourseId(int courseId) {
        return listHomeworksByCourseIdByType(courseId, HomeworkDto.Type.EXAM);
    }

    @Override
    public List<HomeworkDto> listPracticesByCourseId(int courseId) {
        return listHomeworksByCourseIdByType(courseId, HomeworkDto.Type.PRACTICE);
    }

    @Override
    public HomeworkDto create(HomeworkDto newHomework) {

        return null;
    }

    @Override
    public HomeworkDto update(HomeworkDto newHomework) {
        return null;
    }
}
