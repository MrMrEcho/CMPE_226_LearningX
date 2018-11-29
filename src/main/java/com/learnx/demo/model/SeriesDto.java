package com.learnx.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDto {
    private Integer id;
    private String title;
    private String description;

    private Integer instituteId;
    private List<Integer> courseIds;
}
