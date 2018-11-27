package com.learnx.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homework {
    private long id;
    private String title;
    private Course course;
    private String content;

    public static enum Type{
        PRACTICE, EXAM
    }
//    private String contents;
//    private String due;
//
//    public Homework(String c, String d) {
//        this.contents = c;
//        this.due = d;
//    }
//
//    public String getContents() {
//        return contents;
//    }
//
//    public void setContents(String contents) {
//        this.contents = contents;
//    }
//
//    public String getDue() {
//        return due;
//    }
//
//    public void setDue(String due) {
//        this.due = due;
//    }

}
