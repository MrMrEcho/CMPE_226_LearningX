package com.learnx.demo.service;

import com.learnx.demo.model.MaterialDto;
import java.util.List;

public interface MaterialService {

    List<MaterialDto> listMaterialsByCourseId(int courseId);

    MaterialDto create(MaterialDto material);

    MaterialDto update(MaterialDto material);
}
