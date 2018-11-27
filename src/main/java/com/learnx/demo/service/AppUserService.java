package com.learnx.demo.service;

import com.learnx.demo.model.AppUser;

public interface AppUserService {

    AppUser authenticate(String username, String password) throws Exception;

    AppUser Create(String username, String password, AppUser.Role role);
}
