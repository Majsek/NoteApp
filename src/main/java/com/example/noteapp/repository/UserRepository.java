package com.example.noteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.noteapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
