package com.example.noteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.noteapp.model.Note;
import com.example.noteapp.service.NoteService;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes";
    }

    @GetMapping("/boards/{boardId}/notes/new")
    public String createNoteForm(@PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        model.addAttribute("note", new Note());
        return "note-form";
    }

    @PostMapping("/boards/{boardId}/notes/save")
    public String saveNote(@PathVariable("boardId") Long boardId, @ModelAttribute Note note) {
        note.setBoardId(boardId);
        noteService.save(note);
        return "redirect:/boards/" + boardId;
    }

}
