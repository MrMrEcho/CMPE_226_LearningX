package com.learnx.demo.service;

import com.learnx.demo.entity.Material;
import com.learnx.demo.model.MaterialDto;
import com.learnx.demo.repository.MaterialRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository repository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository repository) {
        this.repository = repository;
    }

    private static Material toEntity(MaterialDto dto) {
        Material entity = new Material();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setUrl(dto.getUrl());
        entity.setCourseId(dto.getCourseId());

        return entity;
    }

    @Override
    public List<MaterialDto> listMaterialsByCourseId(int courseId) {
        return RepositoryUtil
                .mapAll(repository.findByCourseId(courseId), MaterialServiceImpl::toDto);
    }

    private static MaterialDto toDto(Material entity) {
        MaterialDto dto = new MaterialDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setUrl(entity.getUrl());
        dto.setCourseId(entity.getCourseId());

        return dto;
    }

    @Override
    public MaterialDto create(MaterialDto dto) {
        Material newEntity = toEntity(dto);
        Material saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public MaterialDto update(MaterialDto dto) {
        Material newEntity = repository.update(toEntity(dto));

        return toDto(newEntity);
    }
}
