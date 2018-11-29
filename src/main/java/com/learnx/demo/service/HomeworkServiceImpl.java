package com.learnx.demo.service;

import com.learnx.demo.entity.Homework;
import com.learnx.demo.model.HomeworkDto;
import com.learnx.demo.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private HomeworkRepository repository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository repository) {
        this.repository = repository;
    }

    private static HomeworkDto toDto(Homework entity) {
        HomeworkDto dto = new HomeworkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setType(HomeworkDto.Type.getEnum(entity.getType()));

        return dto;
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

    @Override
    public List<HomeworkDto> listHomeworksByCourseId(int courseId) {
        return null;
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
