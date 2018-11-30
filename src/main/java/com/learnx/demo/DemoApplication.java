package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DemoApplication {

    @Autowired
    AppUserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //Run code to run after initialization
            System.out.println("starting spring");
            userRepository.save(new AppUser("student", "student", AppUser.STUDENT));
            userRepository.save(new AppUser("instructor", "instructor", AppUser.INSTRUCTOR));
            userRepository.save(new AppUser("institute", "institute", AppUser.INSTITUTE));
            userRepository.save(new AppUser("admin", "admin", AppUser.ADMIN));
        };
    }

}
