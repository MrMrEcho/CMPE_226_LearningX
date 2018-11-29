package com.learnx.demo.service;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository repository;

    @Autowired
    public AppUserServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    private static AppUser toEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAppRole(dto.getRole().getValue());

        return entity;
    }

    private static AppUserDto toDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(AppUserDto.Role.getEnum(entity.getAppRole()));

        return dto;
    }

    @Override
    public AppUserDto authenticate(AppUserDto userDto) {
        AppUser user = repository.findByName(userDto.getUsername());
        if (user == null || !passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        return toDto(user);
    }

    @Override
    public AppUserDto create(AppUserDto userDto) {
        AppUser user = repository.findByName(userDto.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("username already exists!");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        //System.out.println("encodedPassword = " + encodedPassword);
        AppUser userToSave = new AppUser(userDto.getUsername(), encodedPassword, AppUserDto.Role.STUDENT.getValue());
        AppUser newUser = repository.save(userToSave);
        return toDto(newUser);
    }

    @Override
    public AppUserDto update(AppUserDto newUserDto) {
        return null;
    }

    @Override
    public boolean isEnrollByCourseId(int studentId, int courseId) {
        return false;
    }

    @Override
    public boolean isCompleteByCourseId(int studentId, int courseId) {
        return false;
    }

    @Override
    public boolean enrollByCourseId(int studentId, int courseId) {
        return false;
    }

    @Override
    public boolean dropByCourseId(int studentId, int courseId) {
        return false;
    }

    @Override
    public AppUserDto getUserById(int userId) {
        return toDto(repository.findById(userId));
    }

    @Override
    public List<AppUserDto> listInstructorsByInstituteId(int instituteId) {
        return null;
    }
}
