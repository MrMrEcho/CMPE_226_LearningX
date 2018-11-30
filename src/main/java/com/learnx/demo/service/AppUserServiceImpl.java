package com.learnx.demo.service;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.repository.AppUserRepository;
import com.learnx.demo.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AppUserServiceImpl(PasswordEncoder passwordEncoder,
            AppUserRepository userRepository, CourseRepository courseRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public AppUserDto getUserById(int userId) {
        AppUser result = userRepository.findById(userId);
        if (result == null) {
            return null;
        }
        return toDto(result);
    }

    protected static AppUserDto toDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(AppUser.Role.getEnum(entity.getAppRole()));

        return dto;
    }

    @Override
    public List<AppUserDto> listInstructorsByInstituteId(int instituteId) {
        return null;
    }

    @Override
    public AppUserDto authenticate(AppUserDto dto) {
        AppUser user = userRepository.findByName(dto.getUsername());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        return toDto(user);
    }

    @Override
    public AppUserDto create(AppUserDto dto) {
        if (userRepository.findByName(dto.getUsername()) != null) {
            throw new IllegalArgumentException("username already exists!");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        //System.out.println("encodedPassword = " + encodedPassword);
        AppUser newEntity = new AppUser(dto.getUsername(), encodedPassword,
                dto.getRole().getValue());
        AppUser saveEntity = userRepository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public AppUserDto update(AppUserDto newDto) {
        existUser(newDto.getId());
        AppUser newEntity = userRepository.update(toEntity(newDto));

        return toDto(newEntity);
    }

    @Override
    public AppUserDto update(AppUserDto newDto, boolean passwordUpdate) {
        existUser(newDto.getId());
        if (passwordUpdate) {
            String encodedPassword = passwordEncoder.encode(newDto.getPassword());
            newDto.setPassword(encodedPassword);
        }
        AppUser newEntity = userRepository.update(toEntity(newDto));

        return toDto(newEntity);
    }


    private void existUser(int userId) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("User not exist");
        }
    }

    protected static AppUser toEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAppRole(dto.getRole().getValue());

        return entity;
    }

    @Override
    public boolean isEnrollByCourseId(int studentId, int courseId) {
        existUser(studentId);
        existCourse(courseId);

        return userRepository.isEnrollByCourseId(studentId, courseId);
    }

    private void existCourse(int courseId) {
        if (!courseRepository.exists(courseId)) {
            throw new IllegalArgumentException("Course not exist");
        }
    }

    @Override
    public boolean isCompleteByCourseId(int studentId, int courseId) {
        existUser(studentId);
        existCourse(courseId);

        return false;
    }

    @Override
    public boolean enrollByCourseId(int studentId, int courseId) {
        existUser(studentId);
        existCourse(courseId);

        return false;
    }

    @Override
    public boolean dropByCourseId(int studentId, int courseId) {
        existUser(studentId);
        existCourse(courseId);

        return false;
    }

}
