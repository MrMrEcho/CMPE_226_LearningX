package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataWarehouse {

    @Autowired
    AppUserRepository userRepository;

    public void generate() {
        userRepository.save(new AppUser("student", "student", AppUser.STUDENT));
        userRepository.save(new AppUser("instructor", "instructor", AppUser.INSTRUCTOR));
        userRepository.save(new AppUser("institute", "institute", AppUser.INSTITUTE));
        userRepository.save(new AppUser("admin", "admin", AppUser.ADMIN));
    }
}
