package com.learnx.demo.repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RepositoryUtil {

    public static <E, T> List<T> mapAll(Collection<E> list, Function<E, T> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> T findOneResult(List<?> list, Class<T> cls) {
        List<T> results = castAll(list, cls);

        if (results.size() == 1) {
            return results.get(0);
        }
        return null;
    }

    public static <T> List<T> castAll(Collection<?> list, Class<T> cls) {
        return list.stream().map(cls::cast).collect(Collectors.toList());
    }

    public static Integer getLastInsertId(EntityManager em) {
        String sql = "SELECT last_insert_id()";
        Query query = em.createNativeQuery(sql);
        BigInteger bi = (BigInteger) query.getSingleResult();

        return bi.intValue();
    }

    public static String unionQuery(List<String> queryList) {
        String res = queryList.stream().map(q -> "(" + q + ")").collect(Collectors.joining(" union "));
        return res;
    }
}
