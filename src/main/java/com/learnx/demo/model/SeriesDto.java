package com.learnx.demo.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
