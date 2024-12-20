package com.example.noteapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.User;

@Service
public interface Interface_MyUserDetailsService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByUsername(String username);

    void save(User user);

    boolean existsByUsername(String username); // Nová metoda
}
