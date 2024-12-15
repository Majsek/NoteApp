package com.example.noteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.noteapp.model.User;
import com.example.noteapp.service.Interface_MyUserDetailsService;

@Controller
public class AuthController {

    private final Interface_MyUserDetailsService userService;

    @Autowired
    public AuthController(Interface_MyUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return "redirect:/login?error=Passwords do not match";
        }

        if (userService.existsByUsername(username)) {
            return "redirect:/register?error=Username already exists";
        }

        // Vytvoření uživatele
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Heslo bude zašifrováno v UserServiceImpl

        // Logika registrace uživatele
        userService.save(user);

        return "redirect:/login?success=Registered successfully";
    }

    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "custom-register";
    }
}
