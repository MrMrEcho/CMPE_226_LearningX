package com.learnx.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private int id;
    private String title;
    private String description;
    AppUserDto institute;
    List<Course> courses;

}
