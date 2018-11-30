package com.learnx.demo.model;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@SqlResultSetMapping(
        name = "SubmissionDto",
        entities = {
                @EntityResult(
                        entityClass = SubmissionDto.class,
                        fields = {
                                @FieldResult(name = "studentId", column = "S.id"),
                                @FieldResult(name = "studentName", column = "S.name")
                        }
                )
        }
)

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDto {

    private Integer studentId;
    private Integer homeworkId;

    @Setter
    private String answer;
    @Setter
    private int grade;
    private boolean hasGrade;

    public SubmissionDto(int studentId, int homeworkId) {
        this.studentId = studentId;
        this.homeworkId = homeworkId;
    }
}
