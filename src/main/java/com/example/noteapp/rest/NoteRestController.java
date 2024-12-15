package com.example.noteapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.noteapp.model.Note;
import com.example.noteapp.service.NoteService;

import jakarta.validation.Valid;

@RestController
public class NoteRestController {

    private NoteService noteService;

    @Autowired
    public NoteRestController(NoteService noteService){
        this.noteService = noteService;
    }
    
    @GetMapping("/note/{id}")
    public Note noteDetail(@PathVariable Long id){
        return noteService.getNoteById(id);
    }

    @PostMapping("/note")
    public boolean noteCreate(@Valid @RequestBody Note note){
        noteService.addNote(note);
        return true;
    }

    @PutMapping("/note")
    public boolean noteUpdate(@Valid @RequestBody Note note){
        noteService.updateNote(note);
        return true;
    }

    @DeleteMapping("/note/{id}")
    public Note noteDelete(@PathVariable Long id){
        return noteService.deleteNoteById(id);
    }
}
