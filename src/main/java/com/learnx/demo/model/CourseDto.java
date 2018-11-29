package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer id;
    private String title;
    private String description;

    private Integer instructorId;
}
