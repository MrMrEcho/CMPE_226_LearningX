package com.learnx.demo.repository;

import com.learnx.demo.entity.Material;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        String sql =
                "SELECT M.* "
                        + "FROM Material M INNER JOIN Course C ON M.courseId = C.id "
                        + "WHERE C.id = :courseId";
        Query query = em.createNativeQuery(sql, Material.class).setParameter("courseId", courseId);

        return RepositoryUtil.castAll(query.getResultList(), Material.class);
    }
}
