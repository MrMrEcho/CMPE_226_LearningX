package com.learnx.demo.repository;

import com.learnx.demo.entity.Homework.Type;
import com.learnx.demo.entity.Submission;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmissionRepository {

    private final EntityManager em;

    @Autowired
    public SubmissionRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Submission save(Submission entity) {
        // FIXME: This might not correct implementation
        String sql =
                "INSERT INTO Submission (studentId, homeworkId) " +
                        "VALUES (:studentId, :homeworkId)";
        Query query = em.createNativeQuery(sql)
                .setParameter("studentId", entity.getStudentId())
                .setParameter("homeworkId", entity.getHomeworkId());
        if (query.executeUpdate() == 0) {
            return null;
        }

        Submission saveEntity = new Submission();
        saveEntity.setStudentId(entity.getStudentId());
        saveEntity.setHomeworkId(entity.getHomeworkId());
        saveEntity.setAnswer(saveEntity.getAnswer());
        saveEntity.setGrade(saveEntity.getGrade());
        saveEntity.setHasGrade(saveEntity.isHasGrade());

        return saveEntity;
    }

    @Transactional
    public Submission update(Submission newEntity) {
        String sql =
                "UPDATE Submission " +
                        "SET answer = :answer, grade = :grade, hasGrade = :hasGrade " +
                        "WHERE studentId = :studentId AND homeworkId = :homeworkId";

        Query query = em.createNativeQuery(sql)
                .setParameter("answer", newEntity.getAnswer())
                .setParameter("grade", newEntity.getGrade())
                .setParameter("hasGrade", newEntity.isHasGrade())
                .setParameter("studentId", newEntity.getStudentId())
                .setParameter("homeworkId", newEntity.getHomeworkId());

        if (query.executeUpdate() == 0) {
            return null;
        }

        return newEntity;
    }

    public boolean exists(int studentId, int homeworkId) {
        return findById(studentId, homeworkId) != null;
    }

    public Submission findById(int studentId, int homeworkId) {
        String sql = "SELECT * FROM Submission " +
                "WHERE studentId = :studentId AND homeworkId = :homeworkId";
        Query query = em.createNativeQuery(sql, Submission.class)
                .setParameter("studentId", studentId)
                .setParameter("homeworkId", homeworkId);

        return RepositoryUtil.findOneResult(query.getResultList(), Submission.class);
    }

    public List<Submission> findSubmissionByCourseId(int courseId, boolean hasGrade, Type type) {
        String sql = "SELECT S.*" +
                "FROM Submission S INNER JOIN Homework H ON S.homeworkId = H.id " +
                "WHERE courseId =:courseId AND hasGrade = :hasGrade AND H.type = :type";

        Query query = em.createNativeQuery(sql, Submission.class)
                .setParameter("courseId", courseId)
                .setParameter("hasGrade", hasGrade)
                .setParameter("type", type.getValue());

        return RepositoryUtil.castAll(query.getResultList(), Submission.class);
    }

    public List<Submission> findSubmissionByCourseId(int courseId, Type type) {
        String sql = "SELECT S.*" +
                "FROM Submission S INNER JOIN Homework H ON S.homeworkId = H.id " +
                "WHERE courseId =:courseId AND H.type = :type";

        Query query = em.createNativeQuery(sql, Submission.class)
                .setParameter("courseId", courseId)
                .setParameter("type", type.getValue());

        return RepositoryUtil.castAll(query.getResultList(), Submission.class);
    }
}
