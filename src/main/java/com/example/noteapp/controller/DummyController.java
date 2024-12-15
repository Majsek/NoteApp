package com.example.noteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DummyController {
    @GetMapping("/ggg")
    String displayDefaultMessage(){
        return "gg";
    }
}

