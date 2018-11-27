package com.learnx.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private long id;
    private String title;
    AppUser institute;
    List<Course> courses;

}
