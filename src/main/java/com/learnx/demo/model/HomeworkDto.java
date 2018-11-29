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
public class HomeworkDto {

    private Integer id;
    private String title;
    private String content;
    private Type type;
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
