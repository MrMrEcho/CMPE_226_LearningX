package com.learnx.demo.repository;

import com.learnx.demo.entity.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DiscussionRepository {

    private final EntityManager em;

    @Autowired

    public DiscussionRepository(EntityManager em) {
        this.em = em;
    }

    public List<Discussion> findByCourseId(int courseId) {
        String sql = "SELECT D.* " +
                "FROM Discussion D INNER JOIN Course C ON D.courseId = C.id " +
                "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Discussion.class).
                setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Discussion.class);
    }
}
