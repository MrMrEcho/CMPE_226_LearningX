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

    private final CourseRepository repository;

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {
        List<Course> results = repository.search(keyword);
        for (Course course : results) {
            System.out.println("course.getTitle() = " + course.getTitle());
        }

        return RepositoryUtil.mapAll(results, CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCourses() {
        return RepositoryUtil.mapAll(repository.findAll(), CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesSortedByRating(boolean ascending) {
        return RepositoryUtil.mapAll(
                repository.findCourseOrderByRating(ascending),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCourseByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                repository.findCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listOnGoingCoursesByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                repository.findOnGoingCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listFinishedCoursesByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                repository.findFinishedCourseByStudentId(studentId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesByInstructorId(int instructorId) {
        return RepositoryUtil.mapAll(
                repository.findCourseByInstructorId(instructorId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesByInstituteId(int instituteId) {
        return RepositoryUtil.mapAll(
                repository.findCourseByInstituteId(instituteId),
                CourseServiceImpl::toDto);
    }

    @Override
    public List<CourseDto> listCoursesBySeriesId(int seriesId) {
        return RepositoryUtil.mapAll(
                repository.findCourseBySeriesId(seriesId),
                CourseServiceImpl::toDto);
    }

    @Override
    public CourseDto getCourseById(int courseId) {
        Course result = repository.findById(courseId);
        if (result == null) {
            return null;
        }

        return toDto(result);
    }

    @Override
    public CourseDto create(CourseDto dto) {
        Course newEntity = toEntity(dto);
        Course saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
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
    public CourseDto update(CourseDto newDto) {
        Course newEntity = repository.update(toEntity(newDto));

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
