package com.learnx.demo.repository;

import com.learnx.demo.entity.Material;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MaterialRepository {

    private final EntityManager em;

    @Autowired
    public MaterialRepository(EntityManager em) {
        this.em = em;
    }

    public List<Material> findByCourseId(int courseId) {
        String sql = "SELECT M.* " +
                "FROM Material M INNER JOIN Course C ON M.courseId = C.id " +
                "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Material.class)
                .setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Material.class);
    }

    @Transactional
    public Material save(Material entity) {
        String sql = "INSERT INTO Material (courseId, title, url) " +
                "VALUES (:courseId, :title, :url)";
        Query query = em.createNativeQuery(sql)
                .setParameter("courseId", entity.getCourseId())
                .setParameter("title", entity.getTitle())
                .setParameter("url", entity.getUrl());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Material saveEntity = new Material();
        saveEntity.setId(RepositoryUtil.getLastInsertId(em));
        saveEntity.setCourseId(entity.getCourseId());
        saveEntity.setTitle(entity.getTitle());
        saveEntity.setUrl(entity.getUrl());

        return saveEntity;
    }

    @Transactional
    public Material update(Material newEntity) {
        String sql = "UPDATE Material " +
                "SET courseId = :courseId, title = :title, url = :url " +
                "WHERE id = :id";
        Query query = em.createNativeQuery(sql)
                .setParameter("courseId", newEntity.getCourseId())
                .setParameter("title", newEntity.getTitle())
                .setParameter("url", newEntity.getUrl())
                .setParameter("id", newEntity.getId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        return newEntity;
    }
}
