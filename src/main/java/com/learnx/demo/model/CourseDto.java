package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private int id;
    private String title;
    private String description;

    private int instructorId;
}
