package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {
    //@Setter(AccessLevel.NONE)
    private Integer id;
    private String title;
    private String url;

    private Integer courseId;
}
