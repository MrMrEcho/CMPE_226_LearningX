package com.learnx.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Submission")
public class Submission implements Serializable {

    @Id
    private Integer studentId;
    @Id
    private Integer homeworkId;
    private String answer;
    private int grade;
    private boolean hasGrade;

    public Submission(Integer studentId, Integer homeworkId) {
        this.studentId = studentId;
        this.homeworkId = homeworkId;
    }
}
