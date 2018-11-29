package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private int rating;

    private Integer studentId;
    private Integer courseId;
}
