package com.learnx.demo.service;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.repository.AppUserRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository repository;

    @Autowired
    public AppUserServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public AppUserDto getUserById(int userId) {
        AppUser result = repository.findById(userId);
        if (result == null) {
            return null;
        }
        return toDto(result);
    }

    static AppUserDto toDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(AppUser.Role.getEnum(entity.getAppRole()));

        return dto;
    }

    @Override
    public List<AppUserDto> listInstructorsByInstituteId(int instituteId) {
        return RepositoryUtil.mapAll(
                repository.findInstructorByInstituteId(instituteId),
                AppUserServiceImpl::toDto);
    }

    @Override
    public boolean workFor(int instructorId, int instituteId) {
        return repository.workFor(instructorId, instituteId);
    }

    @Override
    public AppUserDto authenticate(AppUserDto dto) {
        AppUser user = repository.findByName(dto.getUsername());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        return toDto(user);
    }

    @Override
    public AppUserDto create(AppUserDto dto) {
        if (repository.findByName(dto.getUsername()) != null) {
            throw new IllegalArgumentException("username already exists!");
        }
        AppUser newEntity = new AppUser(dto.getUsername(), dto.getPassword(),
                dto.getRole().getValue());
        AppUser saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public AppUserDto update(AppUserDto newDto) {
        AppUser newEntity = repository.update(toEntity(newDto), false);

        return toDto(newEntity);
    }

    @Override
    public AppUserDto update(AppUserDto newDto, boolean passwordUpdate) {
        AppUser newEntity = repository.update(toEntity(newDto), true);

        return toDto(newEntity);
    }

    @Override
    public boolean hasEnrolled(int studentId, int courseId) {
        return repository.hasEnrollCourse(studentId, courseId);
    }

    @Override
    public boolean hasCompleted(int studentId, int courseId) {
        return repository.hasCompletedCourse(studentId, courseId);
    }

    @Override
    public boolean hasDropped(int studentId, int courseId) {
        return repository.hasDroppedCourse(studentId, courseId);
    }

    @Override
    public boolean enrollCourse(int studentId, int courseId) {

        if(hasEnrolled(studentId, courseId)) {
            throw new IllegalArgumentException("Student already enrolled course");
        }

        return repository.enrollCourse(studentId, courseId);
    }

    @Override
    public boolean dropCourse(int studentId, int courseId) {

        if(hasEnrolled(studentId, courseId)) {
            throw new IllegalArgumentException("Student had not enrolled course");
        }

        return repository.dropCourse(studentId, courseId);
    }

    private static AppUser toEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAppRole(dto.getRole().getValue());

        return entity;
    }
}
