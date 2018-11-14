package com.learnx.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {
    @RequestMapping("")
    public String index(Model model) {
        System.out.println("first page");
        model.addAttribute("message", "hello from server");
        return "greeting";
    }
}
