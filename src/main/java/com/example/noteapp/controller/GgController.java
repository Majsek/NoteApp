package com.example.noteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GgController {
    @GetMapping("/gg")
    public String gg(Model model) {
        model.addAttribute("title", "GG");
        return "gg";
    }
}