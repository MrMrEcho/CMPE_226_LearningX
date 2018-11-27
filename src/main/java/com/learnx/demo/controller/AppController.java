package com.learnx.demo.controller;

import com.learnx.demo.model.*;
import com.learnx.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    AppUserService appuserService;

    @Autowired
    SeriesService seriesService;

    @Autowired
    CourseService courseService;

    @Autowired
    RatingService rateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showIndex(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("series", seriesService.listSeries());
        return mav;
    }

    //  ======================
    //        login
    //  ======================

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request) {
        ModelAndView mav = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            AppUser user = appuserService.authenticate(username, password);
            mav = new ModelAndView("welcome");
            mav.addObject("username", user.getUsername());
        } catch(Exception e){
            mav.addObject("message", e.getMessage());
            mav = new ModelAndView("login");
        }
        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView showLogout(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("logout");
        return mav;
    }


    //  ======================
    //         sign up
    //  ======================

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AppUser.Role role = AppUser.Role.valueOf(request.getParameter("type"));
        ModelAndView mav = null;
        try {
            AppUser newUser = appuserService.Create(username, password, role);
            mav = new ModelAndView("login");
        } catch (Exception e) {
            mav.addObject("message", e.getMessage());
            mav = new ModelAndView("register");
        }
        return mav;
    }


    //  ======================
    //        search
    //  ======================

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("search_result");
        List<Course> list = courseService.searchCourses(request.getParameter("keyword"));
        mav.addObject("courselist", list);
        return mav;
    }


    //  ======================
    //        courses
    //  ======================

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ModelAndView courses(HttpServletRequest request) {
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        ModelAndView mav = new ModelAndView("single_course");
        Course course = courseService.getCourseById(courseId);
        List<Discussion> discussionList = course.getDiscussions();
        mav.addObject("title", course.getTitle());
        mav.addObject("instructor", course.getInstructor());
        mav.addObject("discussionList", discussionList);
        return mav;
    }

    //  ======================
    //        reviews
    //  ======================

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ModelAndView getreviews(HttpServletRequest request) {
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        ModelAndView mav = new ModelAndView("course_review");
        Course course = courseService.getCourseById(courseId);
        List<Rating> rateList =  rateService.listRatingsByCourseId(courseId);
        mav.addObject("rateList", rateList);
        mav.addObject("title", course.getTitle());
        mav.addObject("instructor", course.getInstructor());
        return mav;
    }

    //  ======================
    //        homework
    //  ======================

    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public ModelAndView gethomework(HttpServletRequest request) {
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        ModelAndView mav = new ModelAndView("course_homework");
        Course course = courseService.getCourseById(courseId);
        List<Homework> homeworkList = course.getHomeworks();
        mav.addObject("homeworkList", homeworkList);
        mav.addObject("title", course.getTitle());
        mav.addObject("instructor", course.getInstructor());
        return mav;
    }
}
