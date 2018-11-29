package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {
    private int id;
    private String title;
    private String url;

    private int courseId;
}
