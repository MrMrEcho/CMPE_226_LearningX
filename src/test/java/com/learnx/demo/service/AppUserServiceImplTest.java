package com.learnx.demo.service;

import static org.junit.Assert.assertEquals;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
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

    private AppUser entity;
    private AppUserDto dto;


    @Test
    public void create() {
        entity = new AppUser("ABC", "Pa$sWord", AppUser.ADMIN);
        dto = AppUserServiceImpl.toDto(entity);

        AppUserDto saveDto = service.create(dto);
        assertEquals(saveDto.getUsername(), dto.getUsername());
        assertEquals(saveDto.getRole(), dto.getRole());
    }

    @Test
    public void update() {
        entity = new AppUser("DEF", "Pa$sWord", AppUser.ADMIN);
        dto = AppUserServiceImpl.toDto(entity);

        AppUserDto saveDto = service.create(dto);
        saveDto.setUsername("newName");
        saveDto.setPassword("Password");
        saveDto.setRole(AppUser.Role.INSTITUTE);

        AppUserDto newDto = service.update(saveDto, true);
        assertEquals(newDto.getUsername(), "newName");
        assertEquals(newDto.getRole(), AppUser.Role.INSTITUTE);
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void listInstructorsByInstituteId() {
    }

    @Test
    public void authenticate() {
    }

    @Test
    public void isEnrollByCourseId() {
    }

    @Test
    public void isCompleteByCourseId() {
    }

    @Test
    public void enrollByCourseId() {
    }

    @Test
    public void dropByCourseId() {
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void hasEnrolled() {
//
//        service.hasEnrolled(1, 1);
//
//        service.hasEnrolled(1, 2);
//
//        service.hasEnrolled(4, 1);
//    }
}
