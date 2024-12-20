package com.example.noteapp.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.noteapp.model.Note;

@Service
public interface Interface_NoteService {

    List<Note> getAllNotes();
    
    Note getNoteById(Long id);

    boolean addNote(Note note);

    boolean updateNote(Note note);

    Note deleteNoteById(Long id);
}


