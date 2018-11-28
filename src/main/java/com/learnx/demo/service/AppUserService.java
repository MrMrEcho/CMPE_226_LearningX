package com.learnx.demo.service;

import com.learnx.demo.model.AppUserDto;

import java.util.List;

public interface AppUserService {

    AppUserDto create(String username, String password, AppUserDto.Role role);

    AppUserDto getUserById(int userId);

    List<AppUserDto> listInstructorsByInstituteId(int instituteId);

    /**
     *
     * @param userDto
     * @return
     * @throws IllegalArgumentException
     */
    AppUserDto authenticate(AppUserDto userDto);
}
