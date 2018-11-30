package com.learnx.demo.controller;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.Homework;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.model.HomeworkDto;
import com.learnx.demo.model.MaterialDto;
import com.learnx.demo.model.SubmissionDto;
import com.learnx.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/courseInteraction")
public class CourseInteractionController {

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/{courseId}")
    public ModelAndView showCourseDetails(HttpSession session, @PathVariable int courseId){
        int userId;
        if (session != null && session.getAttribute("userid")!=null) {
            userId = Integer.valueOf(String.valueOf(session.getAttribute("userid")));
        } else {
            //The user has not login yet, send the user to login
            return new ModelAndView("redirect:/login");
        }
        AppUserDto appUserDto=appUserService.getUserById(userId);
        AppUser.Role role = appUserDto.getRole();
        ModelAndView mav = null;
        switch (role){
            /**
             * Instructor's work
             * 1. Add material
             * 2. Add homework
             * 3. Grade submission
             */
            case INSTRUCTOR:

                mav=new ModelAndView("courseInteraction_instructor");
                mav.addObject("currentCourseId",courseId);
                //add material
                MaterialDto materialDto = new MaterialDto();
                materialDto.setCourseId(courseId);
                mav.addObject("material", materialDto);
                //add homework
                mav.addObject("homework",new HomeworkDto());
                //show homework lists and goto submissions in that
                List<HomeworkDto> homeworkDtos = homeworkService.listHomeworksByCourseId(courseId);
                mav.addObject("homeworks",homeworkDtos);

                break;
            case STUDENT:
                mav=new ModelAndView("courseInteraction_student");
                break;
            default:
                return new ModelAndView("index");

        }


        return mav;
    }

    @PostMapping("/addMaterial")
    public ModelAndView addMaterial(@ModelAttribute("material") MaterialDto material){
        int courseId=material.getCourseId();
        String targetUrl="redirect://courseInteraction/"+courseId;
        materialService.create(material);
        ModelAndView mav=new ModelAndView(targetUrl);

        return mav;
    }

}
