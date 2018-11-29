package com.learnx.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private int type;

    private Integer courseId;

    //    public Homework(String title, String content, int type) {
    //        this.title = title;
    //        this.content = content;
    //        this.type = type;
    //    }
    //
    //    public Homework(String title, String content, int type, Integer courseId) {
    //        this(title, content, type);
    //        this.courseId = courseId;
    //    }
}
