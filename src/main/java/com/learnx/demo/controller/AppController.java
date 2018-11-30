package com.learnx.demo.controller;

import com.learnx.demo.entity.AppUser.Role;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.model.CourseDto;
import com.learnx.demo.model.DiscussionDto;
import com.learnx.demo.model.HomeworkDto;
import com.learnx.demo.model.MaterialDto;
import com.learnx.demo.model.RatingDto;
import com.learnx.demo.model.SearchDto;
import com.learnx.demo.service.AppUserService;
import com.learnx.demo.service.CourseService;
import com.learnx.demo.service.DiscussionService;
import com.learnx.demo.service.HomeworkService;
import com.learnx.demo.service.MaterialService;
import com.learnx.demo.service.RatingService;
import com.learnx.demo.service.SeriesService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;


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
            userDto.setRole(Role.STUDENT);
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView showSearch(ModelMap modelMap) {
        modelMap.put("searchDto", new SearchDto());
        return new ModelAndView("search_result", modelMap);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("search") SearchDto searchDto, ModelMap modelMap) {
        List<CourseDto> courseList = courseService.searchCourses(searchDto.getKeyword());
        modelMap.put("courseList", courseList);
        modelMap.put("searchDto",searchDto);
        return new ModelAndView("search_result", modelMap);
    }


    //  ======================
    //      single series
    //  ======================

    @RequestMapping(value = "/singleSeries/{seriesId}", method = RequestMethod.GET)
    public ModelAndView singleSeries(HttpServletRequest request, @PathVariable(value="seriesId") String seriesId) {
        ModelAndView mav = new ModelAndView("search_result");
        int id = Integer.valueOf(seriesId);
        List<CourseDto> courseList = courseService.listCoursesBySeriesId(id);
        SearchDto searchDto = new SearchDto("");
        if(seriesService.getSeriesById(id) != null) {
            searchDto.setKeyword(seriesService.getSeriesById(id).getTitle());
        }
        mav.addObject("courseList", courseList);
        mav.addObject("searchDto",searchDto);
        return mav;
    }


    //  ======================
    //      single courses
    //  ======================


    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public ModelAndView singleCourses(@PathVariable(value="id") String id, HttpSession session, ModelMap modelMap) {
        int courseId = Integer.valueOf(id);
        DiscussionDto discussionDto =  new DiscussionDto();

        if (session != null && session.getAttribute("userid") != null) {
            int userid = Integer.valueOf(String.valueOf(session.getAttribute("userid")));
            modelMap.put("enroll", userService.hasEnrolled(userid, courseId));
            modelMap.put("complete", userService.hasCompleted(userid, courseId));
            discussionDto.setUserId(userid);
        }

        CourseDto course = courseService.getCourseById(courseId);
        List<DiscussionDto> discussionList = discussionService.listDiscussionsByCourseId(courseId);
        HashMap<Integer, String> nameMap = new HashMap<>();
        if(discussionList != null) {
            for(DiscussionDto d : discussionList) {
                AppUserDto user = userService.getUserById(d.getUserId());
                nameMap.put(d.getUserId(), user.getUsername());
            }
        }

        discussionDto.setCourseId(courseId);
        modelMap.put("course", course);
        modelMap.put("nameMap", nameMap);
        modelMap.put("question", discussionDto);
        modelMap.put("instructor", userService.getUserById(course.getInstructorId()).getUsername());
        modelMap.put("discussionList", discussionList);
        modelMap.put("average_rating", rateService.getAverageRatingByCourseId(courseId));
        return new ModelAndView("single_course", modelMap);
    }

    //  ======================
    //      all courses
    //  ======================

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView allCourses(ModelMap modelMap) {
        List<CourseDto> courseList = courseService.listCourses();
        List<CourseDto> mostPopList = courseService.listCoursesSortedByRating(false);
        List<CourseDto> leastPopList = courseService.listCoursesSortedByRating(true);
        modelMap.put("search", new SearchDto());
        modelMap.put("courseList", courseList);
        modelMap.put("mostPopList", mostPopList);
        modelMap.put("leastPopList", leastPopList);
        return new ModelAndView("courses", modelMap);
    }

    //  ======================
    //        Ratings
    //  ======================


    @RequestMapping(value = "/courses/rating/{id}", method = RequestMethod.GET)
    public ModelAndView getReviews(@PathVariable(value="id") String id) {
        int courseId = Integer.valueOf(id);
        ModelAndView mav = new ModelAndView("course_review");
        CourseDto course = courseService.getCourseById(courseId);
        List<RatingDto> rateList = rateService.listRatingsByCourseId(courseId);
        HashMap<Integer, String> nameMap = new HashMap<>();
        if(rateList != null) {
            for(RatingDto r : rateList) {
                AppUserDto user = userService.getUserById(r.getStudentId());
                nameMap.put(r.getStudentId(), user.getUsername());
            }
        }
        mav.addObject("rateList", rateList);
        mav.addObject("nameMap", nameMap);
        mav.addObject("course", course);
        mav.addObject("instructor", userService.getUserById(course.getInstructorId()).getUsername());
        return mav;
    }

    //  ======================
    //        homework
    //  ======================

    @RequestMapping(value = "/courses/homework/{id}", method = RequestMethod.GET)
    public ModelAndView getHomework(@PathVariable(value="id") String id) {
        int courseId = Integer.valueOf(id);
        ModelAndView mav = new ModelAndView("course_homework");
        CourseDto course = courseService.getCourseById(courseId);
        List<HomeworkDto> homeworkList = homeworkService.listHomeworksByCourseId(courseId);
        mav.addObject("homeworkList", homeworkList);
        mav.addObject("course", course);
        mav.addObject("instructor", userService.getUserById(course.getInstructorId()).getUsername());
        return mav;
    }

    //  ======================
    //        Material
    //  ======================

    @RequestMapping(value = "/courses/material/{id}", method = RequestMethod.GET)
    public ModelAndView getMaterial(@PathVariable(value="id") String id) {
        int courseId = Integer.valueOf(id);
        ModelAndView mav = new ModelAndView("course_material");
        CourseDto course = courseService.getCourseById(courseId);
        List<MaterialDto> materialList = materialService.listMaterialsByCourseId(courseId);
        mav.addObject("materialList", materialList);
        mav.addObject("course", course);
        mav.addObject("instructor", userService.getUserById(course.getInstructorId()).getUsername());
        return mav;
    }

    //  ======================
    //       discusssion
    //  ======================

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.POST)
    public ModelAndView postDiscussion(HttpServletRequest request, @PathVariable(value="id") String id,
                                       @ModelAttribute("question") DiscussionDto newDiscussion, ModelMap modelMap) {
        System.out.println(newDiscussion.getCourseId());
        System.out.println(newDiscussion.getUserId());
        System.out.println(newDiscussion.getTitle());
        System.out.println(newDiscussion.getContent());
        CourseDto course = courseService.getCourseById(newDiscussion.getCourseId());
        int  courseId = course.getId();
        int userId = newDiscussion.getCourseId();

        discussionService.create(newDiscussion);
        List<DiscussionDto> discussionList = discussionService.listDiscussionsByCourseId(courseId);
        HashMap<Integer, String> nameMap = new HashMap<>();
        if(discussionList != null) {
            for(DiscussionDto d : discussionList) {
                int userid = d.getUserId();
                nameMap.put(userid, userService.getUserById(userid).getUsername());
            }
        }
        newDiscussion.setTitle("");
        newDiscussion.setContent("");
        modelMap.put("enroll", userService.hasEnrolled(userId, courseId));
        System.out.println("enroll:"+userService.hasEnrolled(userId, courseId));
        System.out.println("complete:"+userService.hasCompleted(userId, courseId));
        modelMap.put("complete", userService.hasCompleted(userId, courseId));
        modelMap.put("course", course);
        modelMap.put("nameMap", nameMap);
        modelMap.put("question", newDiscussion);
        modelMap.put("instructor", userService.getUserById(course.getInstructorId()).getUsername());
        modelMap.put("discussionList", discussionList);
        modelMap.put("average_rating", rateService.getAverageRatingByCourseId(courseId));
        return new ModelAndView("single_course", modelMap);
    }

    //  ======================
    //      enroll courses
    //  ======================

    @RequestMapping(value = "/enrollCourse", method = RequestMethod.GET)
    public void enrollCourse(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        String userid = request.getParameter("euserid");
        String courseid = request.getParameter("ecourseid");

        int uid = Integer.valueOf(userid);
        int cid = Integer.valueOf(courseid);
        boolean status = userService.enrollCourse(uid, cid);
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(status));
    }
}
