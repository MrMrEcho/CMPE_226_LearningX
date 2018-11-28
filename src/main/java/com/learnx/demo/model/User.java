package com.learnx.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "AppUserDto")
public class User {

    @Id
    @Column(name = "User_Name", length = 64, nullable = false)
    private String username;

    @Column(name = "User_password", length = 128, nullable = false)
    private String password;

    // FIXME: Replace with ENUM type
    @Column(name = "User_type", length = 10, nullable = false)
    private String type;

    // FIXME: email length only 50?
    @Column(name = "Email", length = 256, nullable = false)
    private String email;

}
