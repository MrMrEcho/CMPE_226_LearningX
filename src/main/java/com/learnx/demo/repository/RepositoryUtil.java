package com.learnx.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryUtil {

    public static <T> List<T> toList(List<?> list, Class<T> cls) {
        return list.stream().filter(cls::isInstance).map(cls::cast).collect(Collectors.toList());
    }
}
