package com.learnx.demo.model;

import com.learnx.demo.entity.AppUser.Role;
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
public class AppUserDto {

    private Integer id;
    private String username;
    private String password;
    private Role role;

}
