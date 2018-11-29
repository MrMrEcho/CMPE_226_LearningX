package com.learnx.demo.model;

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
public class SubmissionDto {

    private Integer id;
    private String answer;
    private int grade;
    private boolean isGraded;

    private Integer instructorId;
    private Integer homeworkId;
}
