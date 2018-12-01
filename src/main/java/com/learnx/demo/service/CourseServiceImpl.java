package com.learnx.demo.service;

import com.learnx.demo.entity.Course;
import com.learnx.demo.model.CourseDto;
import com.learnx.demo.repository.CourseRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
        return RepositoryUtil.mapAll(
                courseRepository.findCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listOnGoingCoursesByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                courseRepository.findOnGoingCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listFinishedCoursesByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                courseRepository.findFinishedCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesByInstructorId(int instructorId) {
        return RepositoryUtil.mapAll(
                courseRepository.findCourseByInstructorId(instructorId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesByInstituteId(int instituteId) {
        return RepositoryUtil.mapAll(
                courseRepository.findCourseByInstituteId(instituteId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesBySeriesId(int seriesId) {
        return RepositoryUtil.mapAll(
                courseRepository.findCourseBySeriesId(seriesId),
                CourseServiceImpl::toDto);
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
