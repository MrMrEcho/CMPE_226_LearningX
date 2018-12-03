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
@Table(name = "AppUser")
public class AppUser {

    public static final int ADMIN = Role.ADMIN.value;
    public static final int STUDENT = Role.STUDENT.value;
    public static final int INSTITUTE = Role.INSTITUTE.value;
    public static final int INSTRUCTOR = Role.INSTRUCTOR.value;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private int appRole;

    public AppUser(String username, String password, int appRole) {
        this.username = username;
        this.password = password;
        this.appRole = appRole;
    }

    public enum Role {
        NONE(-1),
        STUDENT(0),
        INSTRUCTOR(1),
        INSTITUTE(2),
        ADMIN(3);

        private final int value;

        Role(int value) {
            this.value = value;
        }

        public static Role getEnum(int value) {
            for (Role e : Role.values()) {
                if (e.getValue() == value) {
                    return e;
                }
            }
            return Role.NONE; // For values out of enum scope
        }

        public int getValue() {
            return value;
        }
    }
}
