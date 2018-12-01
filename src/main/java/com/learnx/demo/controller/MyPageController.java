package com.learnx.demo.controller;


import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.AppUser.Role;
import com.learnx.demo.entity.Course;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.model.CourseDto;
import com.learnx.demo.model.SeriesDto;
import com.learnx.demo.service.AppUserService;
import com.learnx.demo.service.CourseService;
import com.learnx.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/me")
public class MyPageController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SeriesService seriesService;

    @GetMapping()
    public ModelAndView showMyPage(HttpSession session) {

        int userId;
        if (session != null && session.getAttribute("userid")!=null) {
            userId = Integer.valueOf(String.valueOf(session.getAttribute("userid")));
        } else {
            //The user has not login yet, send the user to login
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = null;
        AppUserDto appUserDto = appUserService.getUserById(userId);
        Role role = appUserDto.getRole();

        switch (role) {
            case STUDENT:
                mav = new ModelAndView("myPage_student");
                List<CourseDto> courses_ongoing = courseService.listOnGoingCoursesByStudentId(userId);
                List<CourseDto> courses_past = courseService.listFinishedCoursesByStudentId(userId);
                List<SeriesDto> certificates = seriesService.listSeriesByStudentId(userId);
                mav.addObject("student", appUserDto);
                mav.addObject("courses_ongoing", courses_ongoing);
                mav.addObject("courses_past", courses_past);
                mav.addObject("certificates", certificates);
                break;
            case INSTRUCTOR:
                mav = new ModelAndView("myPage_instructor");
                mav.addObject("instructor", appUserDto);
                List<CourseDto> courses = courseService.listCoursesByInstructorId(userId);
                mav.addObject("courses", courses);
                break;
            case INSTITUTE:
//                TODO Working now
                mav = new ModelAndView("myPage_institute");
                mav.addObject("institute", appUserDto);
                //add instructor
                mav.addObject("instructor", new AppUserDto());
                //add new course
                mav.addObject("course", new CourseDto());
                //update course (assign instructor/update description)
                mav.addObject("newCourse", new CourseDto());
                List<CourseDto> coursesByInstitute = courseService.listCoursesByInstituteId(userId);
                List<AppUserDto> instructorsByInstitute = appUserService.listInstructorsByInstituteId(userId);
                mav.addObject("coursesByInstitute", coursesByInstitute);
                mav.addObject("instructorsByInstitute", instructorsByInstitute);
//              //add new series
                mav.addObject("series", new SeriesDto());
                //add course to series
                mav.addObject("newSeries", new SeriesDto());
                List<SeriesDto> seriesByInstitute = seriesService.listSeriesByInstituteId(userId);
                mav.addObject("seriesByInstitute", seriesByInstitute);
                break;
            case ADMIN:
                mav = new ModelAndView("myPage_admin");
                mav.addObject("newInstitute", new AppUserDto());
                break;
            default:
                return null;
        }
        return mav;
    }

    @PostMapping("/addInstitute")
    public ModelAndView addInstitute(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("newInstitute") AppUserDto institute) {
        try {
            institute.setRole(AppUser.Role.INSTITUTE);
            appUserService.create(institute);
        } catch (Exception e) {
            System.out.print("Something WENT WRONG");
        }
//        System.out.println("Jump back???");
//        System.out.println(institute.getUsername());
//        System.out.println(institute.getPassword());
//        System.out.println(institute.getRole());
        ModelAndView mav = new ModelAndView("redirect:/me");

        return mav;
    }

    @PostMapping("/addCourse")
    public ModelAndView addCourse(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("course") CourseDto course) {
        ModelAndView mav = null;
        courseService.create(course);
        mav = new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/updateCourseInstructor")
    public ModelAndView updateCourseInstructor(HttpServletRequest request, HttpServletResponse response,
                                               @ModelAttribute("newCourse") CourseDto newCourse) {
        //title stores the course's id
        //description stores the instructor's id
        ModelAndView mav = null;
        int newCourseId = Integer.valueOf(newCourse.getTitle());
        int newInstructorId = Integer.valueOf(newCourse.getDescription());
        CourseDto previousCourse = courseService.getCourseById(newCourseId);
        AppUserDto newInstructor = appUserService.getUserById(newInstructorId);
        previousCourse.setInstructorId(newInstructorId);
        courseService.update(previousCourse);
//        System.out.println(newCourse.getTitle());
//        System.out.println(newCourse.getDescription());
//        System.out.println(newCourse.getInstructor().getUsername());
        mav = new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/updateCourse")
    public ModelAndView updateCourse(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("newCourse") Course newCourse) {
        //title stores the course's id
        //description stores the new description
        ModelAndView mav = null;
        int newCourseId = Integer.valueOf(newCourse.getTitle());
        CourseDto previousCourse = courseService.getCourseById(newCourseId);
        previousCourse.setDescription(newCourse.getDescription());
        courseService.update(previousCourse);

//        System.out.println(newCourse.getTitle());
//        System.out.println(newCourse.getDescription());
//        courseService.update(previousCourse);
        mav = new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/addSeries")
    public ModelAndView addSeries(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("series") SeriesDto series) {
        ModelAndView mav = null;
        seriesService.create(series);
        System.out.println(series.getTitle());
        System.out.println(series.getDescription());
        mav = new ModelAndView("redirect:/me");
        return mav;
    }

    //TODO working on this
    @PostMapping("/addCourseToSeries")
    public ModelAndView addCourseToSeries(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("newSeries") SeriesDto newSeries) {
        ModelAndView mav = null;
        int seriesId = Integer.valueOf(newSeries.getTitle());
        int courseId = Integer.valueOf(newSeries.getDescription());
        seriesService.addCourseBySeriesId(courseId, seriesId);
        mav = new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/addInstructor")
    public ModelAndView addInstructor(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("instructor") AppUserDto instructor) {
        ModelAndView mav = null;
        AppUserDto newInstructor = new AppUserDto();
        newInstructor.setUsername(instructor.getUsername());
        newInstructor.setPassword(instructor.getPassword());
        newInstructor.setRole(AppUser.Role.INSTRUCTOR);

        try {
//            appUserService.save(instructor.getUsername(), instructor.getPassword(), AppUserDto.Role.INSTRUCTOR);
            newInstructor = appUserService.create(newInstructor);
            // set work for relationship
            appUserService.workFor(newInstructor.getId(), instituteId);

        } catch (Exception e) {
//            TODO: check if error is handled later
            System.out.println("Could not add new institute, check please.");
            return new ModelAndView("redirect:/me?error=Invalid Data");
        }

        mav = new ModelAndView("redirect:/me");
        return mav;
    }

}
