package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private AppUserRepository repository;

    private static void resultEquals(AppUser result, AppUser user) {
        assertNotNull(result);
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
        assertEquals(result.getAppRole(), user.getAppRole());
    }

    @Test
    public void testSave() {
        AppUser user = new AppUser("abc", "Passw0rd", 1);

        AppUser result = repository.save(user);
        resultEquals(result, user);
    }

    @Test
    @Transactional
    public void testFindByName() {
        AppUser user = new AppUser("def", "Passw0rd", 2);
        em.persist(user);

        AppUser result = repository.findByName(user.getUsername());
        resultEquals(result, user);
    }
}