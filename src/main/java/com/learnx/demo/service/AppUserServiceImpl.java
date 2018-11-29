package com.learnx.demo.service;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AppUserRepository userRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUserDto authenticate(AppUserDto userDto) {
        AppUser user = userRepository.findByName(userDto.getUsername());
        if(user == null || !passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Incorrect username or password!");
        }
        return convertToDto(user);
    }

    @Override
    public AppUserDto create(AppUserDto userDto) {
        AppUser user = userRepository.findByName(userDto.getUsername());
        if(user != null){
            throw new IllegalArgumentException("username already exists!");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        System.out.println("encodedPassword = " + encodedPassword);
        AppUser userToSave = new AppUser(userDto.getUsername(), encodedPassword, AppUserDto.Role.STUDENT.getValue());
        AppUser newUser = userRepository.save(userToSave);
        return convertToDto(userRepository.save(newUser));
    }

    @Override
    public AppUserDto getUserById(int userId) {
        return convertToDto(userRepository.findById(userId));
    }

    @Override
    public List<AppUserDto> listInstructorsByInstituteId(int instituteId) {
        return null;
    }



    private static AppUser convertToEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAppRole(dto.getRole().getValue());

        return entity;
    }

    private static AppUserDto convertToDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(AppUserDto.Role.getEnum(entity.getAppRole()));
        dto.setId(entity.getId());

        return dto;
    }
}
