package com.learnx.demo.repository;

import com.learnx.demo.entity.Discussion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiscussionRepository {

    private final EntityManager em;

    @Autowired
    public DiscussionRepository(EntityManager em) {
        this.em = em;
    }

    public List<Discussion> findByCourseId(int courseId) {
        String sql = "SELECT D.* "
                + "FROM Discussion D INNER JOIN Course C ON D.courseId = C.id "
                + "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Discussion.class)
                .setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Discussion.class);
    }

    @Transactional
    public Discussion save(Discussion entity) {
        String sql = "INSERT INTO Discussion (userId, courseId, title, content) " +
                "VALUES (:userId, :courseId, :title, :content)";
        Query query = em.createNativeQuery(sql, Discussion.class)
                .setParameter("userId", entity.getUserId())
                .setParameter("courseId", entity.getCourseId())
                .setParameter("title", entity.getTitle())
                .setParameter("content", entity.getContent());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Discussion newEntity = new Discussion();
        newEntity.setId(RepositoryUtil.getLastInsertId(em));
        newEntity.setTitle(entity.getTitle());
        newEntity.setContent(entity.getContent());
        newEntity.setCourseId(entity.getCourseId());
        newEntity.setUserId(entity.getUserId());

        return newEntity;
    }

    @Transactional
    public void delete(int id) {
        String sql = "DELETE FROM Discussion WHERE id = :id";
        Query query = em.createNativeQuery(sql)
                .setParameter("id", id);

        query.executeUpdate();
    }
}
