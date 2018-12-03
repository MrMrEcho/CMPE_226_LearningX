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
public class MaterialDto {

    // @Setter(AccessLevel.NONE)
    private Integer id;
    private String title;
    private String url;

    private Integer courseId;
}
