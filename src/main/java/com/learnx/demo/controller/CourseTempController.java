package com.learnx.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course_temp")
public class CourseTempController {
    @GetMapping
    public String showPage(){
        return "course_temp";
    }
}
