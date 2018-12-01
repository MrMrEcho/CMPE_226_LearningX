package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;
import java.util.List;

public interface AppUserService {

    AppUserDto getUserById(int userId);

    List<AppUserDto> listInstructorsByInstituteId(int instituteId);

    AppUserDto authenticate(AppUserDto dto);

    AppUserDto create(AppUserDto dto);

    AppUserDto update(AppUserDto newDto);

    AppUserDto update(AppUserDto newDto, boolean hasUpdatePassword);

    boolean hasEnrolled(int studentId, int courseId);

    boolean hasCompleted(int studentId, int courseId);

    boolean hasDropped(int studentId, int courseId);

    boolean enrollCourse(int studentId, int courseId);

    boolean dropCourse(int studentId, int courseId);

}
