package com.learnx.demo.controller;

import com.learnx.demo.model.AppUser;
import com.learnx.demo.model.Course;
import com.learnx.demo.model.Series;
import com.learnx.demo.service.AppUserService;
import com.learnx.demo.service.CourseService;
import com.learnx.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/me")
public class MyPageController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private AppUserService appUserService;


    @GetMapping()
    public ModelAndView showMyPage(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute AppUser appUser, RedirectAttributes ra){
        //TODO Check if the appUser has passed in successfully later
        AppUser.Role role=appUser.getRole();
        ModelAndView mav=null;
        switch (role){
            case STUDENT:
                mav=new ModelAndView("myPage_student");
                mav.addObject("student",appUser);
                List<Course> courses_ongoing=courseService.listCoursesByUserId(appUser.getId(),false);
                List<Course> courses_past=courseService.listCoursesByUserId(appUser.getId(),true);
                mav.addObject("courses_ongoing",courses_ongoing);
                mav.addObject("courses_past",courses_past);
//                TODO: How to get the certificate(completed series) list?
                List<Series> certificates=seriesService.listSeriesByStudentId(appUser.getId());
                mav.addObject("certificates",certificates);
                break;
            case INSTRUCTOR:
                mav=new ModelAndView("myPage_instructor");
                mav.addObject("instructor",appUser);
                List<Course> courses=courseService.listCoursesByInstructorId(appUser.getId());
                mav.addObject("courses",courses);
//                TODO: Modify the href url in myPage_instructor line 29
                break;
            case INSTITUTE:
                mav=new ModelAndView("myPage_institute");
                mav.addObject("institute",appUser);
                break;
            case ADMIN:
                mav=new ModelAndView("myPage_admin");
                mav.addObject("admin",appUser);
                mav.addObject("institute",new AppUser());
                break;
            default:
                return null;
        }
        return mav;
    }

    @PostMapping("/addInstitute")
    public ModelAndView addInstitute(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute AppUser appUser, RedirectAttributes ra, ModelMap model,
                               @ModelAttribute("institute") AppUser institute){
        ModelAndView mav=null;
        try{
            appUserService.Create(institute.getUsername(), institute.getPassword(), AppUser.Role.INSTITUTE);
        } catch (Exception e){
//            TODO: check if error is handled later
            System.out.println("Could not add new institute, check please.");
            return new ModelAndView("redirect:/me?error=Invalid Data",model);
        }
        mav=new ModelAndView("redirect:/me",model);
        return mav;
    }


}
