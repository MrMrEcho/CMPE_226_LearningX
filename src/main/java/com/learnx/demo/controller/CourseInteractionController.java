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
                //delete material
                List<MaterialDto> materialDtos = materialService.listMaterialsByCourseId(courseId);
                mav.addObject("materials",materialDtos);
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
            /**
             * Student's work
             * 0. Show homeworks
             * 1. Add submission
             * 2. Add ratings to course
             */
            case STUDENT:
                mav=new ModelAndView("courseInteraction_student");
                mav.addObject("currentCourseId",courseId);
                mav.addObject("currentStudentId",userId);
                //show homeworks
                List<HomeworkDto> homeworkDtos = homeworkService.listHomeworksByCourseId(courseId);
                mav.addObject("homeworks", homeworkDtos);

                //add submission for exam
                List<HomeworkDto> examsOfCurrentCourse = homeworkService.listExamsByCourseId(courseId);
                    //The course should have no more than 1 exam
                mav.addObject("examsOfCurrentCourse",examsOfCurrentCourse);
                HomeworkDto theExam=null;
                SubmissionDto sbd=null;
                if(!examsOfCurrentCourse.isEmpty()){
                    theExam=examsOfCurrentCourse.get(0);
                    mav.addObject("theExam",theExam);
                    sbd = new SubmissionDto(userId,theExam.getId());
                    sbd.setHasGrade(false);
                }
                mav.addObject("submissionForExam",sbd);
                //add ratings
                //TODO is this done in the course_review page?
                RatingDto ratingDto = new RatingDto();
                ratingDto.setCourseId(courseId);
                ratingDto.setStudentId(userId);
                mav.addObject("rating",ratingDto);


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

    @PostMapping("deleteMaterial")
    public ModelAndView deleteMaterial(@ModelAttribute("material") MaterialDto material){
        int courseId=material.getCourseId();
        String targetUrl="redirect:/courseInteraction/"+courseId;
        materialService.delete(material.getId());
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

    @PostMapping("/gradeExams/{courseId}")
    public ModelAndView gradeExams(@ModelAttribute("examSubmissions") SubmissionWrapper examSubmissions,
                                   @PathVariable int courseId){
       List<SubmissionDto> submissionDtos=examSubmissions.getSubmissions();
       for(SubmissionDto sub:submissionDtos){
           sub.setHasGrade(true);
           submissionService.update(sub);
       }

       String targetUrl="redirect:/courseInteraction/"+courseId;
       ModelAndView mav=new ModelAndView(targetUrl);

       return mav;
    }

    @PostMapping("/addExamSubmission/{courseId}")
    public ModelAndView addExamSubmission(@ModelAttribute SubmissionDto submissionForExam,
                                          @PathVariable int courseId){

        SubmissionDto dto = submissionForExam;
        if (!submissionService.exist(dto.getStudentId(), dto.getHomeworkId())) {
            dto = submissionService.create(dto);
        }

        submissionService.update(dto);
        String targetUrl="redirect:/courseInteraction/"+courseId;
        ModelAndView mav=new ModelAndView(targetUrl);
        return mav;
    }


}
