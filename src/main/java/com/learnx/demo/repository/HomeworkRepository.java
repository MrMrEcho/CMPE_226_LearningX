package com.learnx.demo.repository;

import com.learnx.demo.entity.Homework;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

@Service
public class HomeworkRepository {

    private final EntityManager em;

    public HomeworkRepository(EntityManager em) {
        this.em = em;
    }

    public Homework save(Homework homework) {

        return null;
    }

    public List<Homework> findByCourseId(int courseId) {
        String sql =
                "SELECT H.* "
                        + "FROM Homework H INNER JOIN Course C ON H.courseId = C.id "
                        + "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Homework.class).setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Homework.class);
    }
}
