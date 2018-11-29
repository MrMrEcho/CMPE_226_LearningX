package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private AppUserRepository repository;


    @Test
    public void testSave() {
        AppUser user = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());

        AppUser result = repository.save(user);
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
        assertEquals(result.getAppRole(), user.getAppRole());
    }

    // TODO: Refactory test code

    @Test
    public void testFindByName() {
        AppUser user = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());
        user = repository.save(user);

        AppUser result = repository.findByName(user.getUsername());
        assertEquals(result, user);
    }

    @Test
    public void testFindById() {
        AppUser user = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());
        user = repository.save(user);

        AppUser result = repository.findById(user.getId());
        assertEquals(result, user);
    }

    @Test
    public void findInstructorById() {
    }

    @Test
    public void findStudentById() {
    }

    @Test
    public void findInstituteById() {
    }
}