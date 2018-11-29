package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDto {
    private int id;
    private String answer;
    private int grade;
    private boolean isGraded;

    private int instructorId;
    private int homeworkId;
}
