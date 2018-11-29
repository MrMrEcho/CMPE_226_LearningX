package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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

        if (n == 0) {
            return null;
        }
        appUser.setId(RepositoryUtil.getLastInsertId(em));

        return appUser;
    }

    public AppUser findByName(String username) {
        String sql = "SELECT id, username, password, approle FROM AppUser " +
                "WHERE username = :name";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("name", username);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    public AppUser findById(int id) {
        String sql = "SELECT id, username, password, approle FROM AppUser " +
                "WHERE id = :id";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("id", id);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find instructor from Instructor view
     *
     * @param instructorId
     * @return Instructor(AppUser)
     */
    public AppUser findInstructorById(int instructorId) {
        String sql = "SELECT id, username, password, approle FROM Instructor " +
                "WHERE id = :instructorId";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("instructorId", instructorId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find student from Student view
     *
     * @param studentId
     * @return
     */
    public AppUser findStudentById(int studentId) {
        String sql = "SELECT id, username, password, approle FROM Student " +
                "WHERE id = :studentId";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("studentId", studentId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find institute from Institute view
     *
     * @param instituteId
     * @return
     */
    public AppUser findInstituteById(int instituteId) {
        String sql = "SELECT id, username, password, approle FROM Institute " +
                "WHERE id = :instituteId";
        Query query = em.createNativeQuery(sql, AppUser.class).
                setParameter("instituteId", instituteId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

}
