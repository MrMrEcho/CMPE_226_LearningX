package com.learnx.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String url;

    private Integer courseId;

//    public Material(String title, String url) {
//        this.title = title;
//        this.url = url;
//    }
//
//    public Material(String title, String url, Integer courseId) {
//        this(title, url);
//        this.courseId = courseId;
//    }
}
