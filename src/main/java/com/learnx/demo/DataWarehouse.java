package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.repository.AppUserRepository;
import com.learnx.demo.repository.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
public class DataWarehouse {

    private static final int ADMIN_NUMBER = 3;
    private static final int INSTITUTE_NUMBER = 3;
    private static final int INSTRUCTOR_NUMBER_PER_INSTITUTE = 3;
    private static final int STUDETN_NUMBER_PER_INSTRUCTOR = 5;

    @Autowired
    AppUserRepository userRepository;
    
    @Autowired
    private EntityManager em;

    @Transactional
    public void generate() {
        generateUsers();
        generateWorkFor();
    }

    protected void generateWorkFor() {
        for(int i = 0; i < INSTITUTE_NUMBER; i++){
            for(int j = 1; j <= INSTRUCTOR_NUMBER_PER_INSTITUTE; j++){
                workFor(i * INSTRUCTOR_NUMBER_PER_INSTITUTE + j, i + 1);
            }
        }
    }

    @Transactional
    protected void workFor(int instructorId, int instituteId) {
        String sql =
                "INSERT INTO WorkFor (instructorId, instituteId) "
                        + "VALUES (:instructorId, :instituteId)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("instructorId", instructorId)
                        .setParameter("instituteId", instituteId);
        query.executeUpdate();
    }

    protected void generateUsers() {
        generateUsers("admin", AppUser.ADMIN, 1);
        generateUsers("institute", AppUser.INSTITUTE, INSTITUTE_NUMBER);
        generateUsers("instructor", AppUser.INSTRUCTOR, INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE);
        generateUsers("student", AppUser.STUDENT, INSTITUTE_NUMBER * INSTRUCTOR_NUMBER_PER_INSTITUTE * STUDETN_NUMBER_PER_INSTRUCTOR);
    }

    private void generateUsers(String username, int role, int count) {
        for (int i = 1; i <= count; i++) {
            userRepository.save(new AppUser(username + i, "123", role));
        }
    }


}
