package com.learnx.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {
    private long id;
    private Course course;
    private AppUser appUser;
    private String title;
    private String content;
}
