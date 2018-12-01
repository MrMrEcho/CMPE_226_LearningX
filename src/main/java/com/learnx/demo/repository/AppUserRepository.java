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

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserRepository(EntityManager em,
            PasswordEncoder passwordEncoder) {
        this.em = em;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser save(AppUser entity) {
        String sql = "INSERT INTO AppUser (username, password, approle) " +
                "VALUES (:username, :password, :role)";
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        Query query = em.createNativeQuery(sql)
                .setParameter("username", entity.getUsername())
                .setParameter("password", entity.getPassword())
                .setParameter("role", entity.getAppRole());

        if (query.executeUpdate() == 0) {
            return null;
        }

        AppUser saveEntity = new AppUser();
        saveEntity.setId(RepositoryUtil.getLastInsertId(em));
        saveEntity.setUsername(entity.getUsername());
        saveEntity.setPassword(entity.getPassword());
        saveEntity.setAppRole(entity.getAppRole());

        return saveEntity;
    }

    @Transactional
    public AppUser update(AppUser entity, boolean hasUpdatePassword) {
        String sql = "UPDATE AppUser U " +
                "SET username = :username, password = :password, appRole = :appRole " +
                "WHERE U.id = :id ";
        if (hasUpdatePassword) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        Query query = em.createNativeQuery(sql)
                .setParameter("username", entity.getUsername())
                .setParameter("password", entity.getPassword())
                .setParameter("appRole", entity.getAppRole())
                .setParameter("id", entity.getId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        return entity;
    }

    public boolean exists(int id) {
        return findById(id) != null;
    }

    public AppUser findById(int id) {
        String sql = "SELECT id, username, password, approle FROM AppUser " + "WHERE id = :id";
        Query query = em.createNativeQuery(sql, AppUser.class).setParameter("id", id);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    public AppUser findByName(String username) {
        String sql = "SELECT id, username, password, approle FROM AppUser WHERE username = :name";
        Query query = em.createNativeQuery(sql, AppUser.class).setParameter("name", username);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    /**
     * Find instructor from Instructor view
     *
     * @return Instructor(AppUser)
     */
    public AppUser findInstructorById(int instructorId) {
        String sql = "SELECT id, username, password, approle FROM Instructor WHERE id = :instructorId";
        Query query = em.createNativeQuery(sql, AppUser.class)
                .setParameter("instructorId", instructorId);

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
        String sql = "SELECT id, username, password, approle FROM Institute WHERE id = :instituteId";
        Query query = em.createNativeQuery(sql, AppUser.class)
                .setParameter("instituteId", instituteId);

        return RepositoryUtil.findOneResult(query.getResultList(), AppUser.class);
    }

    public boolean hasEnrollCourse(int studentId, int courseId) {
        String sql = "SELECT E.studentId " +
                "FROM Enroll E " +
                "WHERE E.studentId = :studentId AND E.courseId = :courseId";
        Query query = em.createNativeQuery(sql)
                .setParameter("studentId", studentId)
                .setParameter("courseId", courseId);

        List<?> results = query.getResultList();

        return results.size() == 1;
    }

    public List<AppUser> findInstructorByInstituteId(int instituteId) {
        String sql = "SELECT T.* " +
                "FROM Instructor T INNER JOIN WorkFor W ON T.id = W.instructorId " +
                "WHERE W.instituteId = :instituteId";
        Query query = em.createNativeQuery(sql, AppUser.class)
                .setParameter("instituteId", instituteId);

        return RepositoryUtil.castAll(query.getResultList(), AppUser.class);
    }

    public boolean hasCompletedCourse(int studentId, int courseId) {
        String sql = "SELECT hasCompleted " +
            "FROM Enroll WHERE studentId =: studentId AND courseId = :courseId";
        Query query = em.createNativeQuery(sql, Boolean.class);

//        List<?> result = query.getResultList();
//        if (results.size() == 0 && (boolean)results.get(0)) {
//
//        }
        return false;
    }

    public boolean hasDroppedCourse(int studentId, int courseId) {
        return false;
    }

    @Transactional
    public boolean enrollCourse(int studentId, int courseId) {
        String sql = "INSERT INTO Enroll (studentId, courseId) VALUES (:studentId, :courseId)";
        Query query = em.createNativeQuery(sql)
                .setParameter("studentId", studentId)
                .setParameter("courseId", courseId);

        return query.executeUpdate() == 1;
    }

    @Transactional
    public boolean dropCourse(int studentId, int courseId) {
        String sql = "UPDATE Enroll SET hasDropped = true WHERE studentId = :studentId AND courseId = :courseId";
        Query query = em.createNativeQuery(sql);

        return query.executeUpdate() == 1;
    }
}
