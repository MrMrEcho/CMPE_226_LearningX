package com.learnx.demo.repository;

import com.learnx.demo.entity.Series;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class SeriesRepository {

    private final EntityManager em;

    public SeriesRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Series create(Series entity) {
        String sql =
                "INSERT INTO Series (title, description, instituteId) " +
                        "VALUES (:title, :description, :instituteId)";
        Query query = em.createNativeQuery(sql)
                .setParameter("title", entity.getTitle())
                .setParameter("description", entity.getDescription())
                .setParameter("instituteId", entity.getInstituteId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        Series saveEntity = new Series();
        saveEntity.setId(RepositoryUtil.getLastInsertId(em));
        saveEntity.setTitle(entity.getTitle());
        saveEntity.setDescription(entity.getDescription());
        saveEntity.setInstituteId(entity.getInstituteId());

        return saveEntity;
    }

    @Transactional
    public Series update(Series newEntity) {
        String sql =
                "UPDATE Series SET " +
                        "title = :title, description = :description, instituteId = :instituteId " +
                        "WHERE id = :id";
        Query query = em.createNativeQuery(sql)
                .setParameter("title", newEntity.getTitle())
                .setParameter("description", newEntity.getDescription())
                .setParameter("instituteId", newEntity.getInstituteId())
                .setParameter("id", newEntity.getId());

        if (query.executeUpdate() == 0) {
            return newEntity;
        }

        return newEntity;
    }

    public List<Series> findAll() {
        String sql = "SELECT * FROM Series";
        Query query = em.createNativeQuery(sql, Series.class);

        return RepositoryUtil.castAll(query.getResultList(), Series.class);
    }

    public Series findById(int courseId) {
        String sql = "SELECT * FROM Series WHERE id = :courseId";
        Query query = em.createNativeQuery(sql, Series.class)
                .setParameter("id", courseId);

        return RepositoryUtil.findOneResult(query.getResultList(), Series.class);
    }

    public List<Series> findByInstituteId(int instituteId) {
        return null;
    }

    public List<Series> findByStudentId(int studentId) {
        return null;
    }
}
