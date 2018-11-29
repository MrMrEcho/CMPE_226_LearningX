package com.learnx.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDto {
    int instituteId;
    List<Integer> courseIds;
    private int id;
    private String title;
    private String description;
}
