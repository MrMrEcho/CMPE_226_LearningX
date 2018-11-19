package com.learnx.demo.controller;

import com.learnx.demo.model.*;
import com.learnx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    //  ======================
    //        login
    //  ======================

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new User());
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login) {
        ModelAndView mav = null;
        User user = userService.validate(login);
        if(user == null){
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        } else{
            mav = new ModelAndView("welcome");
            mav.addObject("username", login.getUsername());
        }
        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView showLogout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("logout");
        return mav;
    }


    //  ======================
    //         sign up
    //  ======================

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("user") User user) {
        User newUser = userService.create(user);
        if(newUser == null){
            throw new RuntimeException("error when signup");
        }
        return new ModelAndView("welcome", "username", newUser.getUsername());
    }


    //  ======================
    //        search
    //  ======================

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("search_result");
        List<Course> list = new ArrayList<>();
        list.add(new Course(1232, "2019 Fall", "HTML 5 for beginner", "good!"));
        list.add(new Course(4543, "2019 Spring", "Java Spring", "good!"));
        mav.addObject("courselist", list);
        return mav;
    }


    //  ======================
    //        courses
    //  ======================

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView courses(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("single_course");
        Course c = new Course(1232, "2019 Fall", "HTML 5 for beginner", "good!");
        mav.addObject("course", c);
        List<Question> list = new ArrayList<>();
        list.add(new Question("Hi!", "HiHi", "2019-04-03"));
        list.add(new Question("Yo!", "YoYo", "2019-03-03"));
        mav.addObject("questionlist", list);
        return mav;
    }

    //  ======================
    //        reviews
    //  ======================

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public ModelAndView getreviews(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("course_review");
        List<Rate> list = new ArrayList<>();
        list.add(new Rate((int) Math.round(3.4 * 20), 3.4, "2019-04-03", "Tom"));
        list.add(new Rate((int) Math.round(4.5 * 20), 4.5, "2019-03-03", "Mary"));
        Course c = new Course(1232, "2019 Fall", "HTML 5 for beginner", "good!");
        mav.addObject("reviewlist", list);
        mav.addObject("course", c);
        return mav;
    }

    //  ======================
    //        homework
    //  ======================

    @RequestMapping(value = "/homework", method = RequestMethod.GET)
    public ModelAndView gethomework(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("course_homework");
        List<Homework> list = new ArrayList<>();
        list.add(new Homework("Project1", "2019-04-03"));
        list.add(new Homework("Project2", "2019-03-03"));
        Course c = new Course(1232, "2019 Fall", "HTML 5 for beginner", "good!");
        mav.addObject("homeworklist", list);
        mav.addObject("course", c);
        return mav;
    }
}
