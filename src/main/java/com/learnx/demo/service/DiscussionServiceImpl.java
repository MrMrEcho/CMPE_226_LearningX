package com.learnx.demo.service;

import com.learnx.demo.entity.Discussion;
import com.learnx.demo.model.DiscussionDto;
import com.learnx.demo.repository.DiscussionRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository repository;

    @Autowired
    public DiscussionServiceImpl(DiscussionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DiscussionDto> listDiscussionsByCourseId(int courseId) {
        return RepositoryUtil
                .mapAll(repository.findByCourseId(courseId), DiscussionServiceImpl::toDto);
    }

    private static DiscussionDto toDto(Discussion entity) {
        DiscussionDto dto = new DiscussionDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setUserId(entity.getUserId());
        dto.setCourseId(entity.getCourseId());

        return dto;
    }

    @Override
    public DiscussionDto create(DiscussionDto dto) {
        Discussion newEntity = toEntity(dto);
        Discussion saveEntity = repository.save(newEntity);
        return toDto(saveEntity);
    }

    private static Discussion toEntity(DiscussionDto dto) {
        Discussion entity = new Discussion();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setUserId(dto.getUserId());
        entity.setCourseId(dto.getCourseId());

        return entity;
    }
}
