package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AppUserRepository {

    private final EntityManager em;

    @Autowired
    public AppUserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public AppUser save(AppUser appUser) {

        String sql = "INSERT INTO AppUser (username, password, approle) " +
                "VALUES (:username, :password, :role)";
        Query query = em.createNativeQuery(sql).
                setParameter("username", appUser.getUsername()).
                setParameter("password", appUser.getPassword()).
                setParameter("role", appUser.getAppRole());
        int n = query.executeUpdate();
        AppUser newAppUser = null;
        if (n == 1) {
            newAppUser = findByName(appUser.getUsername());
        }

        return newAppUser;
    }

    public AppUser findByName(String username) {
        String sql = "SELECT id, username, password, approle FROM AppUser " +
                "WHERE username=:name";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("name", username);
        List<AppUser> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public AppUser findById(int id) {
        String sql = "SELECT id, username, password, approle FROM AppUser " +
                "WHERE id=:id";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("id", id);
        List<AppUser> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

}
