package com.learnx.demo.repository;

import static org.junit.Assert.assertEquals;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private AppUserRepository repository;

    @Test
    public void testSave() {
        AppUser entity = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());

        AppUser result = repository.save(entity);
        assertEquals(result.getUsername(), entity.getUsername());
        assertEquals(result.getPassword(), entity.getPassword());
        assertEquals(result.getAppRole(), entity.getAppRole());
    }

    // TODO: Refactory test code

    @Test
    public void testFindByName() {
        AppUser entity = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());
        entity = repository.save(entity);

        AppUser result = repository.findByName(entity.getUsername());
        assertEquals(result, entity);
    }

    @Test
    public void testFindById() {
        AppUser entity = new AppUser("admin2", "admin2",
                AppUserDto.Role.ADMIN.getValue());
        entity = repository.save(entity);

        AppUser result = repository.findById(entity.getId());
        assertEquals(result, entity);
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

    @Test
    public void isEnrollByCourseId() {

        assertEquals(true, repository.isEnrollByCourseId(1, 1));

        assertEquals(false, repository.isEnrollByCourseId(1, 2));

        assertEquals(false, repository.isEnrollByCourseId(4, 1));
    }
}