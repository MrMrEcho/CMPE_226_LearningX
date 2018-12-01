package com.learnx.demo.repository;

import com.learnx.demo.entity.Series;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeriesRepository {

    private final EntityManager em;

    @Autowired
    public SeriesRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Series save(Series entity) {
        String sql = "INSERT INTO Series (title, description, instituteId) " +
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
        String sql = "UPDATE Series " +
                "SET title = :title, description = :description, instituteId = :instituteId " +
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

    public Series findById(int id) {
        String sql = "SELECT * FROM Series WHERE id = :id";
        Query query = em.createNativeQuery(sql, Series.class)
                .setParameter("id", id);

        return RepositoryUtil.findOneResult(query.getResultList(), Series.class);
    }

    public List<Series> findByInstituteId(int instituteId) {
        String sql = "SELECT * FROM Series WHERE instituteId = :instituteId";
        Query query = em.createNativeQuery(sql, Series.class)
                .setParameter("instituteId", instituteId);

        return RepositoryUtil.castAll(query.getResultList(), Series.class);
    }

    public List<Series> findByStudentId(int studentId) {
        String sql = "SELECT DISTINCT S.id, S.title, S.description, S.instituteId " +
                "FROM CourseSeries C " +
                "INNER JOIN Enroll E ON E.courseId = C.courseId " +
                "INNER JOIN Series S ON S.id = C.seriesId " +
                "WHERE E.studentId = :studentId";
        Query query = em.createNativeQuery(sql, Series.class)
                .setParameter("studentId", studentId);

        return RepositoryUtil.castAll(query.getResultList(), Series.class);
    }

    @Transactional
    public void addCourseToSeriesById(int courseId, int seriesId) {
        String sql = "INSERT INTO CourseSeries (courseId, seriesId) " +
                "VALUES (:courseId, :seriesId)";
        Query query = em.createNativeQuery(sql)
                .setParameter("courseId", courseId)
                .setParameter("seriesId", seriesId);

        if (query.executeUpdate() == 0) {
            return;
        }
    }
}
