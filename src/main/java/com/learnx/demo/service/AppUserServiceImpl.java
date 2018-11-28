package com.learnx.demo.service;

import com.learnx.demo.entity.AppUser;
import com.learnx.demo.model.AppUserDto;
import com.learnx.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUserDto authenticate(String username, String password) {

        AppUser user = appUserRepository.findByName(username);

        if(user == null) {
            throw new NoResultException("username not exist");
        }

        if (!user.getPassword().equals(password)) {
            throw new NoResultException("password not match");
        }

        return convertToDto(user);
    }

    @Override
    public AppUserDto create(String username, String password, AppUserDto.Role role) {
        AppUser user = new AppUser(username, password, role.getValue());
        return convertToDto(appUserRepository.save(user));
    }

    @Override
    public AppUserDto getUserById(int userId) {
        return convertToDto(appUserRepository.findById(userId));
    }

    @Override
    public List<AppUserDto> listInstructorsByInstituteId(long instituteId) {
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
