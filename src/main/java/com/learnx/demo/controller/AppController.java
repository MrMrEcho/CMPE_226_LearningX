package com.learnx.demo.controller;

import com.learnx.demo.model.*;
import com.learnx.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    AppUserService userService;

    @Autowired
    SeriesService seriesService;

    @Autowired
    CourseService courseService;

    @Autowired
    RatingService rateService;

    @Autowired
    DiscussionService discussionService;

    @Autowired
    MaterialService materialService;

    @Autowired
    HomeworkService homeworkService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("seriesList", seriesService.listSeries());
        return mav;
    }

    //  ======================
    //        login
    //  ======================

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(ModelMap modelMap) {
        modelMap.put("user", new AppUserDto());
        return new ModelAndView("login", modelMap);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("user") AppUserDto userDto, ModelMap modelMap, HttpSession session) {
        AppUserDto authUser = null;
        try {
            authUser = userService.authenticate(userDto);
        } catch (IllegalArgumentException e) {
            userDto.setPassword("");
            modelMap.put("user", userDto);
            modelMap.put("message", e.getMessage());
            return new ModelAndView("login", modelMap);
        }
        if (authUser != null) {
            session.setAttribute("username", authUser.getUsername());
            session.setAttribute("userid", authUser.getId());
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView showLogout(HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("index");
        session.invalidate();
        mav.addObject("seriesList", seriesService.listSeries());
        return mav;
    }

    //  ======================
    //         sign up
    //  ======================

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showRegister(ModelMap modelMap) {
        modelMap.put("user", new AppUserDto());
        return new ModelAndView("register", modelMap);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") AppUserDto userDto, ModelMap modelMap) {
        AppUserDto newUser = null;
        try {
            newUser = userService.create(userDto);
        } catch (IllegalArgumentException e) {
            modelMap.put("message", e.getMessage());
            return new ModelAndView("register", modelMap);
        }
        return new ModelAndView("login");
    }


    //  ======================
    //        search
    //  ======================

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("search_result");
        List<CourseDto> courseList = courseService.searchCourses(request.getParameter("keyword"));
        mav.addObject("courseList", courseList);
        return mav;
    }

    //  ======================
    //      single series
    //  ======================

    @RequestMapping(value = "/singleSeries", method = RequestMethod.POST)
    public ModelAndView singleSeries(HttpServletRequest request) {
        int seriesId = Integer.valueOf(request.getParameter("id"));
        ModelAndView mav = new ModelAndView("search_result");
        List<CourseDto> courseList = seriesService.listCoursesBySeriesId(seriesId);
        mav.addObject("courseList", courseList);
        return mav;
    }


    //  ======================
    //      single courses
    //  ======================

    @RequestMapping(value = "/singleCourse", method = RequestMethod.POST)
    public ModelAndView singleCourses(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("single_course");
        int courseId = Integer.valueOf(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userid") != null) {
            int userid = Integer.valueOf(String.valueOf(session.getAttribute("userid")));
            mav.addObject("enroll", userService.isEnrollByCourseId(userid, courseId));
            mav.addObject("complete", userService.isCompleteByCourseId(userid, courseId));
        }

        CourseDto course = courseService.getCourseById(courseId);
        List<DiscussionDto> discussionList = discussionService.listDiscussionsByCourseId(courseId);
        mav.addObject("course", course);
        mav.addObject("instructor", userService.getUserById(course.getInstructorId()));
        mav.addObject("discussionList", discussionList);
        mav.addObject("question", new DiscussionDto());
        mav.addObject("average_rating", rateService.getAverageRatingByCourseId(courseId));
        return mav;
    }

    //  ======================
    //      all courses
    //  ======================

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView allCourses(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("courses");
        List<CourseDto> courseList = courseService.listCourses();
        List<CourseDto> mostPopList = courseService.listCoursesSortedByRating(false);
        List<CourseDto> leastPopList = courseService.listCoursesSortedByRating(true);
        mav.addObject("courseList", courseList);
        mav.addObject("mostPopList", mostPopList);
        mav.addObject("leastPopList", leastPopList);
        return mav;
    }

    //  ======================
    //        Ratings
    //  ======================

    @RequestMapping(value = "/getRatingByCourseId", method = RequestMethod.POST)
    public ModelAndView getreviews(HttpServletRequest request) {
        int courseId = Integer.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("course_review");
        CourseDto course = courseService.getCourseById(courseId);
        List<RatingDto> rateList = rateService.listRatingsByCourseId(courseId);
        mav.addObject("rateList", rateList);
        mav.addObject("course", course);
        return mav;
    }

    //  ======================
    //        homework
    //  ======================

    @RequestMapping(value = "/getHomeworkByCourseId", method = RequestMethod.POST)
    public ModelAndView gethomework(HttpServletRequest request) {
        int courseId = Integer.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("course_homework");
        CourseDto course = courseService.getCourseById(courseId);
        List<HomeworkDto> homeworkList = homeworkService.listHomeworksByCourseId(courseId);
        mav.addObject("homeworkList", homeworkList);
        mav.addObject("course", course);
        return mav;
    }

    //  ======================
    //        Material
    //  ======================

    @RequestMapping(value = "/getMaterialByCourseId", method = RequestMethod.POST)
    public ModelAndView getMaterial(HttpServletRequest request) {
        int courseId = Integer.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("course_material");
        CourseDto course = courseService.getCourseById(courseId);
        List<MaterialDto> materialList = materialService.listMaterialsByCourseId(courseId);
        mav.addObject("materialList", materialList);
        mav.addObject("course", course);
        return mav;
    }

    //  ======================
    //       discusssion
    //  ======================

    @RequestMapping(value = "/discussion", method = RequestMethod.POST)
    public ModelAndView postDiscussion(HttpServletRequest request,
                                       @ModelAttribute("discussion") DiscussionDto newDiscussion) {
        int userid = Integer.valueOf(request.getParameter("userid"));
        int courseid = Integer.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("course_homework");
        CourseDto course = courseService.getCourseById(courseid);
        AppUserDto user = userService.getUserById(userid);
        newDiscussion.setUserId(user.getId());
        newDiscussion.setCourseId(course.getId());

        discussionService.create(newDiscussion);
        List<DiscussionDto> discussionList = discussionService.listDiscussionsByCourseId(courseid);
        mav.addObject("course", course);
        mav.addObject("discussionList", discussionList);
        return mav;
    }

    //  ======================
    //      enroll courses
    //  ======================

    @RequestMapping(value = "/enrollCourse", method = RequestMethod.POST)
    public ModelAndView enrollCourse(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userid"));
        int courseId = Integer.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("single_course");
        CourseDto course = courseService.getCourseById(courseId);
        userService.enrollByCourseId(userId, course.getId());
        List<DiscussionDto> discussionList = discussionService.listDiscussionsByCourseId(course.getId());
        mav.addObject("enroll", userService.isEnrollByCourseId(userId, courseId));
        mav.addObject("complete", userService.isCompleteByCourseId(userId, courseId));
        mav.addObject("course", course);
        mav.addObject("discussionList", discussionList);
        mav.addObject("discussion", new DiscussionDto());
        mav.addObject("average_rating", rateService.getAverageRatingByCourseId(courseId));
        return mav;
    }
}
