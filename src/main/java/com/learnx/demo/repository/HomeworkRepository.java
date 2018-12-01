package com.learnx.demo.repository;

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
    public Homework save(Homework entity) {
        String sql = "INSERT INTO Homework (courseId, title, content, type) " +
                "VALUES (:courseId, :title, :content, :type)";
        Query query =
                em.createNativeQuery(sql)
                        .setParameter("courseId", entity.getCourseId())
                        .setParameter("title", entity.getTitle())
                        .setParameter("content", entity.getContent())
                        .setParameter("type", entity.getType());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Homework saveEntity = new Homework();
        saveEntity.setId(RepositoryUtil.getLastInsertId(em));
        saveEntity.setCourseId(entity.getCourseId());
        saveEntity.setTitle(entity.getTitle());
        saveEntity.setContent(entity.getContent());
        saveEntity.setType(entity.getType());

        return saveEntity;
    }

    public List<Homework> findByCourseId(int courseId) {
        String sql = "SELECT H.* " +
                "FROM Homework H INNER JOIN Course C ON H.courseId = C.id " +
                "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Homework.class)
                .setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Homework.class);
    }

    @Transactional
    public Homework update(Homework newEntity) {
        String sql = "UPDATE Homework " +
                "SET courseId = :courseId, title = :title, content = :content , type = :type " +
                "WHERE id = :id";
        Query query = em.createNativeQuery(sql)
                .setParameter("courseId", newEntity.getCourseId())
                .setParameter("title", newEntity.getTitle())
                .setParameter("content", newEntity.getContent())
                .setParameter("type", newEntity.getType())
                .setParameter("id", newEntity.getId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        return newEntity;
    }
}
