package com.alexnmat.exam.service;

import com.alexnmat.exam.models.entities.User;
import com.alexnmat.exam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/*
@Author: MSN & AFC
 */

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsernameWithRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findUserByUsername(auth.getName());
    }

    public User findUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
