package com.example.noteapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.noteapp.model.User;


public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);
}

