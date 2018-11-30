package com.learnx.demo.repository;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.Homework;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class HomeworkRepository {

    private final EntityManager em;

    public HomeworkRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Homework save(Homework toSave) {
        String sql =
                "INSERT INTO Homework (courseId, title, content, type) "
                        + "VALUES (:courseId, :title, :content, :type)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("courseId", toSave.getCourseId())
                        .setParameter("title", toSave.getTitle())
                        .setParameter("content", toSave.getContent())
                        .setParameter("type", toSave.getType());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Homework saved = new Homework();
        saved.setId(RepositoryUtil.getLastInsertId(em));
        saved.setCourseId(toSave.getCourseId());
        saved.setTitle(toSave.getTitle());
        saved.setContent(toSave.getContent());
        saved.setType(toSave.getType());
        return saved;
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
