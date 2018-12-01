package com.learnx.demo.service;

import com.learnx.demo.entity.Course;
import com.learnx.demo.model.CourseDto;
import com.learnx.demo.repository.AppUserRepository;
import com.learnx.demo.repository.CourseRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final AppUserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
            AppUserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    static Course toEntity(CourseDto dto) {
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setInstructorId(dto.getInstructorId());

        return entity;
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {
        List<Course> results = courseRepository.search(keyword);
        for (Course course : results) {
            System.out.println("course.getTitle() = " + course.getTitle());
        }
        return RepositoryUtil.mapAll(results, CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCourses() {
        return RepositoryUtil.mapAll(courseRepository.findAll(), CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesSortedByRating(boolean ascending) {
        return RepositoryUtil.mapAll(
                courseRepository.findCourseOrderByRating(ascending),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCourseByStudentId(int studentId) {
        if (!userRepository.exists(studentId)) {
            throw new IllegalArgumentException("Student not exist");
        }

        return RepositoryUtil.mapAll(
                courseRepository.findCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listOnGoingCoursesByStudentId(int studentId) {
        return null;
    }

    @Override
    public List<CourseDto> listFinishedCoursesByStudentId(int studentId) {
        return null;
    }

    @Override
    public List<CourseDto> listCoursesByInstructorId(int instructorId) {
        return null;
    }

    @Override
    public List<CourseDto> listCoursesByInstituteId(int instructorId) {
        return null;
    }

    @Override
    public List<CourseDto> listCoursesBySeriesId(int seriesId) {
        return null;
    }

    @Override
    public CourseDto getCourseById(int courseId) {

        Course result = courseRepository.findById(courseId);
        if (result == null) {
            return null;
        }

        return toDto(result);
    }

    @Override
    public CourseDto create(CourseDto dto) {
        Course newEntity = toEntity(dto);
        Course saveEntity = courseRepository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public CourseDto update(CourseDto newDto) {
        Course newEntity = courseRepository.update(toEntity(newDto));

        return toDto(newEntity);
    }

    private static CourseDto toDto(Course entity) {
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setInstructorId(entity.getInstructorId());

        return dto;
    }
}
