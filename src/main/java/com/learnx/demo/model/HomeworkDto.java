package com.learnx.demo.model;

import com.learnx.demo.entity.Homework.Type;
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
public class HomeworkDto {

    private Integer id;
    private String title;
    private String content;
    private Type type;
    private Integer courseId;

}
