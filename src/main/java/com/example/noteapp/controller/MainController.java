package com.example.noteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.noteapp.service.Interface_NoteService;

@Controller
public class MainController {

    private Interface_NoteService noteService;

    @Autowired
    public MainController(Interface_NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("notes", noteService.getAllNotes());
        return "redirect:/boards";
    }



    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/boards")
    public String boards() {
        return "boards";
    }

}

