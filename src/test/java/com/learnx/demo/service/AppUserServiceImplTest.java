package com.learnx.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserServiceImplTest {

    @Autowired
    private AppUserService service;

    @Test(expected = IllegalArgumentException.class)
    public void isEnrollByCourseId() {

        service.isEnrollByCourseId(1, 1);

        service.isEnrollByCourseId(1, 2);

        service.isEnrollByCourseId(4, 1);
    }
}