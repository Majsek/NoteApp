package com.example.noteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.noteapp.config.MyUserDetails;
import com.example.noteapp.model.User;
import com.example.noteapp.repository.Interface_UserRepository;
import com.example.noteapp.service.interfaces.Interface_MyUserDetailsService;

@Service
public class MyUserDetailsService implements Interface_MyUserDetailsService {

    private final Interface_UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Přidání PasswordEncoderu

    @Autowired
    public MyUserDetailsService(Interface_UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}