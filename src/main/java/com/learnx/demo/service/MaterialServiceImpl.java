package com.learnx.demo.service;

import com.learnx.demo.entity.Material;
import com.learnx.demo.model.MaterialDto;
import com.learnx.demo.repository.MaterialRepository;
import com.learnx.demo.repository.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository repository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository repository) {
        this.repository = repository;
    }

    private static MaterialDto toDto(Material entity) {
        MaterialDto dto = new MaterialDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setUrl(entity.getUrl());
        dto.setCourseId(entity.getCourseId());

        return dto;
    }

    private static Material toEntity(Material dto) {
        Material entity = new Material();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setUrl(dto.getUrl());
        entity.setCourseId(dto.getCourseId());

        return entity;
    }

    @Override
    public List<MaterialDto> listMaterialsByCourseId(int courseId) {
        return RepositoryUtil.mapAll(repository.findByCourseId(courseId), MaterialServiceImpl::toDto);
    }

    @Override
    public MaterialDto create(MaterialDto material) {
        return null;
    }

    @Override
    public MaterialDto update(MaterialDto material) {
        return null;
    }
}
