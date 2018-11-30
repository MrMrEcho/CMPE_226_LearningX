package com.learnx.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rating")
public class Rating implements Serializable {

    @Id
    private Integer studentId;
    @Id
    private Integer courseId;

    private int rate;

}
