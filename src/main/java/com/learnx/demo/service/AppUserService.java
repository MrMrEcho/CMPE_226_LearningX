package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;
import java.util.List;

public interface AppUserService {

    AppUserDto getUserById(int userId);

    List<AppUserDto> listInstructorsByInstituteId(int instituteId);

    /**
     *
     */
    AppUserDto authenticate(AppUserDto dto);

    AppUserDto create(AppUserDto dto);

    AppUserDto update(AppUserDto newDto);

    AppUserDto update(AppUserDto newDto, boolean passwordUpdate);

    boolean isEnrollByCourseId(int studentId, int courseId);

    boolean isCompleteByCourseId(int studentId, int courseId);

    boolean enrollByCourseId(int studentId, int courseId);

    boolean dropByCourseId(int studentId, int courseId);

}
