package com.learnx.demo.service;

import com.learnx.demo.entity.Homework;
import com.learnx.demo.entity.Homework.Type;
import com.learnx.demo.model.HomeworkDto;
import com.learnx.demo.repository.HomeworkRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository repository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository repository) {
        this.repository = repository;
    }

    private static Homework toEntity(HomeworkDto dto) {
        Homework entity = new Homework();
        entity.setId(dto.getId());
        entity.setCourseId(dto.getCourseId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setType(dto.getType().getValue());

        return entity;
    }

    private static HomeworkDto toDto(Homework entity) {
        HomeworkDto dto = new HomeworkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setType(Homework.Type.getEnum(entity.getType()));

        return dto;
    }

    @Override
    public List<HomeworkDto> listHomeworksByCourseId(int courseId) {
        return RepositoryUtil
                .mapAll(repository.findByCourseId(courseId), HomeworkServiceImpl::toDto);
    }

    @Override
    public List<HomeworkDto> listHomeworksByCourseIdByType(int courseId, Type type) {
        return listHomeworksByCourseId(courseId).stream().
                filter(e -> e.getType() == type).collect(Collectors.toList());
    }

    @Override
    public List<HomeworkDto> listExamsByCourseId(int courseId) {
        return listHomeworksByCourseIdByType(courseId, Homework.Type.EXAM);
    }

    @Override
    public List<HomeworkDto> listPracticesByCourseId(int courseId) {
        return listHomeworksByCourseIdByType(courseId, Homework.Type.PRACTICE);
    }

    @Override
    public HomeworkDto create(HomeworkDto dto) {
        Homework newEntity = toEntity(dto);
        Homework saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public HomeworkDto update(HomeworkDto dto) {
        Homework newEntity = repository.update(toEntity(dto));

        return toDto(newEntity);
    }
}
