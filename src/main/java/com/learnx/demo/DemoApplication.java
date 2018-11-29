package com.learnx.demo;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
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
            AppUser admin = new AppUser("admin", "admin", AppUserDto.Role.ADMIN.getValue());
            userRepository.save(admin);
            AppUser institute = new AppUser("institute", "institute", AppUserDto.Role.INSTITUTE.getValue());
            userRepository.save(institute);
            AppUser instructor = new AppUser("instructor", "instructor", AppUserDto.Role.INSTRUCTOR.getValue());
            userRepository.save(instructor);
            AppUser student = new AppUser("student", "student", AppUserDto.Role.STUDENT.getValue());
            userRepository.save(student);
        };
    }

}
