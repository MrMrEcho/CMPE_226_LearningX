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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    DiscussionService discussionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("series", seriesService.listSeries());
        List<Series> seriesList = new ArrayList<>();
        Series s = new Series(1, "HTML for beginners","1234", new AppUser(1,"ss","sss", AppUser.Role.INSTITUTE), new ArrayList<>());
        seriesList.add(s);
        mav.addObject("seriesList", seriesList);
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
    public ModelAndView loginProcess(HttpServletRequest request, HttpSession session) {
        ModelAndView mav = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
//            AppUser user = appuserService.authenticate(username, password);
            AppUser user = new AppUser(1,"Tom","sss", AppUser.Role.STUDENT);
            mav = new ModelAndView("index");
            //        mav.addObject("series", seriesService.listSeries());
            List<Series> seriesList = new ArrayList<>();
            Series s = new Series(1, "HTML for beginners","1234", new AppUser(1,"ss","sss", AppUser.Role.INSTITUTE), new ArrayList<>());
            seriesList.add(s);
            mav.addObject("seriesList", seriesList);


            mav.addObject("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userid", user.getId());
        } catch(Exception e){
            mav.addObject("message", e.getMessage());
            mav = new ModelAndView("login");
        }
        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView showLogout(HttpServletRequest request , HttpSession session) {
        ModelAndView mav = new ModelAndView("index");
        session.invalidate();
        //        mav.addObject("series", seriesService.listSeries());
        List<Series> seriesList = new ArrayList<>();
        Series s = new Series(1, "HTML for beginners","1234", new AppUser(1,"ss","sss", AppUser.Role.INSTITUTE), new ArrayList<>());
        seriesList.add(s);
        mav.addObject("seriesList", seriesList);

        return mav;
    }


    //  ======================
    //         sign up
    //  ======================

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new AppUser());
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, @ModelAttribute("user") AppUser newUser) {
        ModelAndView mav = null;
        try {
//            AppUser newUser = appuserService.Create(username, password, role);
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
    //      single series
    //  ======================

    @RequestMapping(value = "/singleSeries", method = RequestMethod.POST)
    public ModelAndView singleSeries(HttpServletRequest request) {
        long seriesId = Long.valueOf(request.getParameter("id"));
        ModelAndView mav = new ModelAndView("search_result");
//        List<Course> courseList = seriesService.listCoursesBySeriesId(seriesId);

        List<Course> courseList = new ArrayList<>();
        Course c = new Course(1,"Happy Learn", new AppUser(1,"ss","sss", AppUser.Role.INSTRUCTOR),"descripppp",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        courseList.add(c);
        mav.addObject("courseList", courseList);
        return mav;
    }


    //  ======================
    //      single courses
    //  ======================

    @RequestMapping(value = "/singleCourse", method = RequestMethod.POST)
    public ModelAndView singleCourses(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("single_course");
        long courseId = Long.valueOf(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        if(session != null) {
            Long userid = Long.valueOf(String.valueOf(session.getAttribute("userid")));
//            mav.addObject("enroll", courseService.isEnrolled(courseId, userid));
//            mav.addObject("complete", courseService.isComplete(courseId, userid));
        }

        mav.addObject("enroll", true);
        mav.addObject("complete", false);


//        Course course = courseService.getCourseById(courseId);
//        List<Discussion> discussionList = course.getDiscussions();
        Course course = new Course(1,"Happy Learn", new AppUser(1,"ss","sss", AppUser.Role.INSTRUCTOR),"descripppp",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        List<Discussion> discussionList = new ArrayList<>();
        Discussion d = new Discussion(1,course, new AppUser(1,"ss","sss", AppUser.Role.STUDENT), "why","content");
        discussionList.add(d);
        mav.addObject("course", course);
        mav.addObject("discussionList", discussionList);
        mav.addObject("discussion", new Discussion());
        return mav;
    }
    //  ======================
    //      all courses
    //  ======================

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView allCourses(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("courses");
        List<Course> courseList = courseService.listCourses();
        List<Course> mostPopList = courseService.listCoursesSortedByRating(false);
        List<Course> leastPopList = courseService.listCoursesSortedByRating(true);
        mav.addObject("courseList", courseList);
        mav.addObject("mostPopList", mostPopList);
        mav.addObject("leastPopList", leastPopList);
        return mav;
    }

    //  ======================
    //        reviews
    //  ======================

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ModelAndView getreviews(HttpServletRequest request) {
        long courseId = Long.valueOf(request.getParameter("courseId"));
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
        long courseId = Long.valueOf(request.getParameter("courseId"));
        ModelAndView mav = new ModelAndView("course_homework");
        Course course = courseService.getCourseById(courseId);
        List<Homework> homeworkList = course.getHomeworks();
        mav.addObject("homeworkList", homeworkList);
        mav.addObject("title", course.getTitle());
        mav.addObject("instructor", course.getInstructor());
        return mav;
    }

    //  ======================
    //       discusssion
    //  ======================

    @RequestMapping(value = "/discussion", method = RequestMethod.POST)
    public ModelAndView postDiscussion(HttpServletRequest request, @ModelAttribute("discussion") Discussion newDiscussion) {
        long userid = Long.valueOf(request.getParameter("userid"));
        long courseid = Long.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("course_homework");
        Course course = courseService.getCourseById(courseid);
        AppUser user = appuserService.getUserById(userid);
        newDiscussion.setAppUser(user);
        newDiscussion.setCourse(course);

        discussionService.create(newDiscussion);
        List<Discussion> discussionList = discussionService.listDiscussionsByCourseId(courseid);
        mav.addObject("course", course);
        mav.addObject("discussionList", discussionList);
        return mav;
    }

    //  ======================
    //      enroll courses
    //  ======================

    @RequestMapping(value = "/enrollCourse", method = RequestMethod.POST)
    public ModelAndView enrollCourse(HttpServletRequest request) {
        long userid = Long.valueOf(request.getParameter("userid"));
        long courseid = Long.valueOf(request.getParameter("courseid"));
        ModelAndView mav = new ModelAndView("single_course");
        courseService.enroll(appuserService.getUserById(userid), courseService.getCourseById(courseid));

        return mav;
    }
}
