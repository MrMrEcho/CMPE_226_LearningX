package com.learnx.demo.service;

import com.learnx.demo.model.MaterialDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialServiceImplTest {

    @Autowired
    private MaterialService service;

    @Test
    public void listMaterialsByCourseId() {
        List<MaterialDto> results = service.listMaterialsByCourseId(2);

        results.forEach(System.out::println);
    }
}