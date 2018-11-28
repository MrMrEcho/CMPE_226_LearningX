package com.learnx.demo.service;

import com.learnx.demo.model.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Override
    public List<Material> listMaterialsByCourseId(int courseId) {
        return null;
    }

    @Override
    public Material create(Material material) {
        return null;
    }
}
