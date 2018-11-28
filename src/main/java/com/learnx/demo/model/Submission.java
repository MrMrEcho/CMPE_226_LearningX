package com.learnx.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    private int id;
    private AppUserDto student;
    private Homework homework;
    private String answer;
    private int grade;
    private boolean isGraded;
}
