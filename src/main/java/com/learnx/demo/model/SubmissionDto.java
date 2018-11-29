package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDto {
    private Integer id;
    private String answer;
    private int grade;
    private boolean isGraded;

    private Integer instructorId;
    private Integer homeworkId;
}
