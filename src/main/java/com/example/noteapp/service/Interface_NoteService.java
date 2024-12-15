package com.example.noteapp.service;

import java.util.List;

import com.example.noteapp.model.Note;

public interface Interface_NoteService {

    List<Note> getAllNotes();
    
    Note getNoteById(Long id);

    boolean addNote(Note note);

    boolean updateNote(Note note);

    Note deleteNoteById(Long id);
}


