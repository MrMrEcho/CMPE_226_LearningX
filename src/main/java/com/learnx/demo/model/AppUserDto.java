package com.learnx.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    public enum Role {
        NONE(0),
        STUDENT(1),
        INSTRUCTOR(2),
        INSTITUTE(3),
        ADMIN(4);

        private final int value;
        private Role(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Role getEnum(int value){
            for (Role e:Role.values()) {
                if(e.getValue() == value)
                    return e;
            }
            return Role.NONE;//For values out of enum scope
        }
    }

    private long id;
    private String username;
    private String password;
    private Role role;

}
