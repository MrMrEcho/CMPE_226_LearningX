package com.learnx.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private Integer id;
    private String username;
    private String password;
    private Role role;

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
                if (e.getValue() == value)
                    return e;
            }
            return Role.NONE;//For values out of enum scope
        }

        public int getValue() {
            return value;
        }
    }
}
