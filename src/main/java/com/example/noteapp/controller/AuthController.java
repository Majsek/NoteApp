package com.example.noteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.noteapp.model.User;
import com.example.noteapp.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return "redirect:/login?error=Passwords do not match";
        }

        // Vytvoření uživatele
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Heslo bude zašifrováno v UserServiceImpl

        // Logika registrace uživatele
        userService.save(user);

        return "redirect:/login?success=Registered successfully";
    }
}
