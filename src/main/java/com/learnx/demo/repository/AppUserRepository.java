package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserRepository {

    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    @Autowired
    public AppUserRepository(EntityManager em, PasswordEncoder passwordEncoder) {
        this.em = em;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser save(AppUser entity) {
        String sql =
                "INSERT INTO AppUser (username, password, approle) "
                        + "VALUES (:username, :password, :role)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("username", entity.getUsername())
                        .setParameter("password", passwordEncoder.encode(entity.getPassword()))
                        .setParameter("role", entity.getAppRole());

        if (query.executeUpdate() == 0) {
            return null;
        }
        entity.setId(RepositoryUtil.getLastInsertId(em));

        return entity;
    }

    public AppUser findByName(String username) {
        String sql =
                "SELECT id, username, password, approle FROM AppUser " + "WHERE username = :name";
        Query query = em.createNativeQuery(sql, AppUser.class).setParameter("name", username);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    public AppUser findById(int id) {
        String sql = "SELECT id, username, password, approle FROM AppUser " + "WHERE id = :id";
        Query query = em.createNativeQuery(sql, AppUser.class).setParameter("id", id);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find instructor from Instructor view
     *
     * @return Instructor(AppUser)
     */
    public AppUser findInstructorById(int instructorId) {
        String sql =
                "SELECT id, username, password, approle FROM Instructor "
                        + "WHERE id = :instructorId";
        Query query =
                em.createNativeQuery(sql, AppUser.class).setParameter("instructorId", instructorId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find student from Student view
     */
    public AppUser findStudentById(int studentId) {
        String sql =
                "SELECT id, username, password, approle FROM Student " + "WHERE id = :studentId";
        Query query = em.createNativeQuery(sql, AppUser.class).setParameter("studentId", studentId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find institute from Institute view
     */
    public AppUser findInstituteById(int instituteId) {
        String sql =
                "SELECT id, username, password, approle FROM Institute "
                        + "WHERE id = :instituteId";
        Query query = em.createNativeQuery(sql, AppUser.class)
                .setParameter("instituteId", instituteId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    public boolean isEnrollByCourseId(int studentId, int courseId) {
        String sql =
                "SELECT E.studentId "
                        + "FROM Enroll E "
                        + "WHERE E.studentId = :studentId AND E.courseId = :courseId";
        Query query = em.createNativeQuery(sql)
                .setParameter("studentId", studentId)
                .setParameter("courseId", courseId);

        List<?> results = query.getResultList();

        return results.size() == 1;
    }
}
