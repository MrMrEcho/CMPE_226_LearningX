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
//
//    @Autowired
//    private CourseService courseService;
//    @Autowired
//    private SeriesService seriesService;
//    @Autowired
//    private AppUserService appUserService;
//
//
//    @GetMapping()
//    public ModelAndView showMyPage(HttpServletRequest request, HttpServletResponse response,
//                                   @ModelAttribute AppUser appUser, RedirectAttributes ra){
//        //TODO Check if the appUser has passed in successfully later
//        AppUser.Role role=appUser.getRole();
//        ModelAndView mav=null;
//        switch (role){
//            case STUDENT:
//                mav=new ModelAndView("myPage_student");
//                mav.addObject("student",appUser);
//                List<Course> courses_ongoing=courseService.listCoursesByUserId(appUser.getId(),false);
//                List<Course> courses_past=courseService.listCoursesByUserId(appUser.getId(),true);
//                mav.addObject("courses_ongoing",courses_ongoing);
//                mav.addObject("courses_past",courses_past);
////                TODO: How to get the certificate(completed series) list?
//                List<Series> certificates=seriesService.listSeriesByStudentId(appUser.getId());
//                mav.addObject("certificates",certificates);
//                break;
//            case INSTRUCTOR:
//                mav=new ModelAndView("myPage_instructor");
//                mav.addObject("instructor",appUser);
//                List<Course> courses=courseService.listCoursesByInstructorId(appUser.getId());
//                mav.addObject("courses",courses);
////                TODO: Modify the href url in myPage_instructor line 29
//                break;
//            case INSTITUTE:
//                mav=new ModelAndView("myPage_institute");
//                mav.addObject("institute",appUser);
//                break;
//            case ADMIN:
//                mav=new ModelAndView("myPage_admin");
//                mav.addObject("admin",appUser);
//                mav.addObject("institute",new AppUser());
//                break;
//            default:
//                return null;
//        }
//        return mav;
//    }
//
//    @PostMapping("/addInstitute")
//    public ModelAndView addInstitute(HttpServletRequest request, HttpServletResponse response,
//                                     @ModelAttribute AppUser appUser, RedirectAttributes ra, ModelMap model,
//                               @ModelAttribute("institute") AppUser institute){
//        ModelAndView mav=null;
//        try{
//            appUserService.Create(institute.getUsername(), institute.getPassword(), AppUser.Role.INSTITUTE);
//        } catch (Exception e){
////            TODO: check if error is handled later
//            System.out.println("Could not add new institute, check please.");
//            return new ModelAndView("redirect:/me?error=Invalid Data",model);
//        }
//        mav=new ModelAndView("redirect:/me",model);
//        return mav;
//    }

    @Autowired
    private CourseService courseService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SeriesService seriesService;

    @GetMapping()
    public ModelAndView showMyPage(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute AppUser appUser){
        ModelAndView mav=null;
//        AppUser.Role role=appUser.getRole();
//        ModelAndView mav=null;
//        switch (role){
//            case STUDENT:
//                mav=new ModelAndView("myPage_student");
//                mav.addObject("student",appUser);
//                break;
//            case INSTRUCTOR:
//                mav=new ModelAndView("myPage_instructor");
//                mav.addObject("instructor",appUser);
//                List<Course> courses=courseService.listCoursesByInstructorId(appUser.getId());
//                mav.addObject("courses",courses);
//                break;
//            case INSTITUTE:
//                mav=new ModelAndView("myPage_institute");
//                mav.addObject("institute",appUser);
//                break;
//            case ADMIN:
//                mav=new ModelAndView("myPage_admin");
//                mav.addObject("admin",appUser);
//                break;
//            default:
//                return null;
//        }
        // For Instructor
//        mav=new ModelAndView("myPage_instructor");
//        List<Course> courses=new ArrayList<>();
//        Course course1=new Course();
//        course1.setTitle("Java8");
//        course1.setDescription("Description of Java8");
//        courses.add(course1);
//
//        Course course2=new Course();
//        course2.setTitle("Spring");
//        course2.setDescription("Description of Spring");
//        courses.add(course2);
//        mav.addObject("courses",courses);

        // For Admin
//        mav=new ModelAndView("myPage_admin");
//        mav.addObject("institute",new AppUser());

        // For Institute
        mav=new ModelAndView("myPage_institute");
        //For add new Course
        mav.addObject("course", new Course());

        //For update existing course
        mav.addObject("newCourse",new Course());


        List<AppUser> instructors=appUserService.listInstructorsByInstituteId(appUser.getId());
        AppUser i1=new AppUser();
        AppUser i2=new AppUser();
        i1.setUsername("Fayad");
        i2.setUsername("Gao");
//        instructors.add(0,i1);
        instructors.add(0,i1);
        instructors.add(0,i2);
        mav.addObject("instructors", instructors);

        List<Course> coursesByInstitute = courseService.listCoursesByInstituteId(appUser.getId());
        Course c1=new Course();
        Course c2=new Course();
        c1.setTitle("226");
        c2.setTitle("275");
        coursesByInstitute.add(0,c1);
        coursesByInstitute.add(0,c2);
        mav.addObject("coursesByInstitute",coursesByInstitute);

        //For Create Series
        mav.addObject("series",new Series());
        //For add course to series
        mav.addObject("newSeries",new Series());
        //For Create new Instructor AppUser
        mav.addObject("instructor",new AppUser());
        return mav;
    }

    @PostMapping("/addInstitute")
    public ModelAndView addInstitute(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("institute") AppUser institute){
//        try{
//            appUserService.Create(institute.getUsername(),institute.getPassword(), AppUser.Role.INSTITUTE);
//        } catch (Exception e){
//            System.out.print("Something WENT WRONG");
//        }
        System.out.println("Jump back！");
        System.out.println(institute.getUsername());
        System.out.println(institute.getPassword());
        System.out.println(institute.getRole());
        ModelAndView mav=new ModelAndView("redirect:/me");

        return mav;
    }

    @PostMapping("/addCourse")
    public ModelAndView addCourse(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("course") Course course){
        ModelAndView mav=null;
        courseService.create(course);
//        System.out.println(course.getTitle());
//        System.out.println(course.getDescription());
        mav=new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/updateCourse")
    public ModelAndView updateCourse(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("newCourse") Course newCourse){
        ModelAndView mav=null;
        courseService.update(newCourse);
        System.out.println(newCourse.getTitle());
        System.out.println(newCourse.getDescription());
//        TODO find how to mapping select into th:value
        System.out.println(newCourse.getInstructor());
        mav=new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/addSeries")
    public ModelAndView addSeries(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("series") Series series){
        ModelAndView mav=null;
        seriesService.create(series);
        System.out.println(series.getTitle());
        System.out.println(series.getDescription());
        mav=new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/addCourseToSeries")
    public ModelAndView addCourseToSeries(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("newSeries") Series newSeries){
        ModelAndView mav=null;
        String courseTitle=newSeries.getDescription();
        //TODO how to add course into series?
//        seriesService.addCourseBySeriesId(courseTitle,newSeries);
        System.out.println(newSeries.getTitle());
        System.out.println(newSeries.getDescription());
        mav=new ModelAndView("redirect:/me");
        return mav;
    }

    @PostMapping("/addInstructor")
    public ModelAndView addInstructor(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("instructor") AppUser instructor){
        ModelAndView mav=null;
        try{
            appUserService.Create(instructor.getUsername(), instructor.getPassword(), AppUser.Role.INSTRUCTOR);
        } catch (Exception e){
//            TODO: check if error is handled later
            System.out.println("Could not add new institute, check please.");
            return new ModelAndView("redirect:/me?error=Invalid Data");
        }
//        System.out.println(course.getTitle());
//        System.out.println(course.getDescription());
        mav=new ModelAndView("redirect:/me");
        return mav;
    }


}
