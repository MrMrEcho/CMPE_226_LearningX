package com.learnx.demo.service;

import com.learnx.demo.model.MaterialDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Override
    public List<MaterialDto> listMaterialsByCourseId(int courseId) {
        return null;
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
