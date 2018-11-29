package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDto {
    private int id;
    private String title;
    private String content;

    private int appuserId;
    private int courseId;
}
