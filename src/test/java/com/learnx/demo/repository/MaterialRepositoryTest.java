package com.learnx.demo.repository;

import com.learnx.demo.entity.Material;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository repository;

    @Test
    public void findByCourseId() {
        List<Material> results = repository.findByCourseId(1);

        results.forEach(System.out::println);
    }
}