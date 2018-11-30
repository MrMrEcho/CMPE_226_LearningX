package com.learnx.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Homework")
public class Homework {

    public static final int PRACTICE = Type.PRACTICE.value;
    public static final int EXAM = Type.EXAM.value;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private int type;
    private Integer courseId;

    public enum Type {
        NONE(-1),
        PRACTICE(0),
        EXAM(1);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public static Type getEnum(int value) {
            for (Type e : Type.values()) {
                if (e.getValue() == value) {
                    return e;
                }
            }
            return Type.NONE; // For values out of enum scope
        }

        public int getValue() {
            return value;
        }
    }

}
