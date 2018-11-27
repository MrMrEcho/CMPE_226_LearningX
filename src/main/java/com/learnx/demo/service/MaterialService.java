package com.learnx.demo.service;

import com.learnx.demo.model.Material;

import java.util.List;

public interface MaterialService {

    List<Material> listMaterialsByCourseId(long courseId);

    // Material CRUD
    Material create(Material material);
}
