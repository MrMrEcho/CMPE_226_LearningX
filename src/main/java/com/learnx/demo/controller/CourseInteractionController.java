package com.learnx.demo.controller;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.entity.Homework.Type;
import com.learnx.demo.model.*;
import com.learnx.demo.service.AppUserService;
import com.learnx.demo.service.HomeworkService;
import com.learnx.demo.service.MaterialService;
import com.learnx.demo.service.RatingService;
import com.learnx.demo.service.SubmissionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
                HomeworkDto homeworkDto = new HomeworkDto();
                homeworkDto.setCourseId(courseId);
                mav.addObject("homework",homeworkDto);
                List<Type> htypelist = new ArrayList<>();
                htypelist.add(Type.PRACTICE);
                htypelist.add(Type.EXAM);
                mav.addObject("htypelist",htypelist);
                //show examSubmissions and grade them
                List<SubmissionDto> exams=submissionService.listExamSubmissionByCourseId(courseId,false);
                SubmissionWrapper examSubmissions=new SubmissionWrapper();
                for(SubmissionDto examSub:exams){
                    examSubmissions.addSubmission(examSub);
                }
                mav.addObject("examSubmissions",examSubmissions);

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
        String targetUrl="redirect:/courseInteraction/"+courseId;
        materialService.create(material);
        ModelAndView mav=new ModelAndView(targetUrl);
        return mav;
    }

    @PostMapping("/addHomework")
    public ModelAndView addHomework(@ModelAttribute("homework") HomeworkDto homeworkDto){
        int courseId=homeworkDto.getCourseId();
        String targetUrl="redirect:/courseInteraction/"+courseId;
        homeworkService.create(homeworkDto);
        ModelAndView mav=new ModelAndView(targetUrl);
        return mav;
    }

    @PostMapping("//gradeExams/{courseId}")
    public ModelAndView gradeExams(@ModelAttribute("examSubmissions") SubmissionWrapper examSubmissions,
                                   @PathVariable int courseId){
       List<SubmissionDto> submissionDtos=examSubmissions.getSubmissions();
       for(SubmissionDto sub:submissionDtos){
           submissionService.update(sub);
       }

       String targetUrl="redirect:/courseInteraction/"+courseId;
       ModelAndView mav=new ModelAndView(targetUrl);

       return mav;
    }


}
