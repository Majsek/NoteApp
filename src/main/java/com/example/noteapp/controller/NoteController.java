package com.example.noteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.noteapp.service.NoteService;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String listNotes(Model model) {
        // Přidej poznámky do modelu a zobraz je ve view
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes";
    }
}

