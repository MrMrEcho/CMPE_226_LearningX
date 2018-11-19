package com.learnx.demo.service;

import com.learnx.demo.dao.UserDAO;
import com.learnx.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User create(User user) {
        return userDAO.save(user);
    }
}
