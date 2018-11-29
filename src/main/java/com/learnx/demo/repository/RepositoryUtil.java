package com.learnx.demo.repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RepositoryUtil {

    public static <T> List<T> toList(List<?> list, Class<T> cls) {
        return list.stream().filter(cls::isInstance).map(cls::cast).collect(Collectors.toList());
    }

    public static <E, T> List<T> convertToDto(List<E> list, Function<E, T> entityToDto) {
        return list.stream().map(entityToDto).collect(Collectors.toList());
    }
}
