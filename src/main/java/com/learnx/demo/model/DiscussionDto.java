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
public class DiscussionDto {

    private Integer id;
    private String title;
    private String content;

    private Integer userId;
    private Integer courseId;
}
