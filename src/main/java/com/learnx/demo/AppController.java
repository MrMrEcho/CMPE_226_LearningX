package com.learnx.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {
    @RequestMapping("")
    public String index(Model model) {
        System.out.println("first page");
        model.addAttribute("message", "hello from server");

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Java", "Jiaqi"));
        courses.add(new Course("Python", "Tianxiang"));
        model.addAttribute("courses", courses);
        return "greeting";
    }
}
