package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;

import java.util.List;

public interface AppUserService {

    AppUserDto getUserById(int userId);

    List<AppUserDto> listInstructorsByInstituteId(int instituteId);

    /**
     * @param userDto
     * @return
     * @throws IllegalArgumentException
     */
    AppUserDto authenticate(AppUserDto userDto);

    AppUserDto create(AppUserDto userDto);

    AppUserDto update(AppUserDto newUserDto);

    boolean isEnrollByCourseId(int studentId, int courseId);

    boolean isCompleteByCourseId(int studentId, int courseId);

    boolean enrollByCourseId(int studentId, int courseId);

    boolean dropByCourseId(int studentId, int courseId);

}
