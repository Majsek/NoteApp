package com.example.noteapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.Note;
import com.example.noteapp.repository.Interface_NoteRepository;
import com.example.noteapp.service.interfaces.Interface_NoteService;

@Service
public class NoteService implements Interface_NoteService {

    private final Interface_NoteRepository noteRepository;

    @Autowired
    public NoteService(Interface_NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addNote(Note note) {
        noteRepository.save(note);
        return true;
    }

    @Override
    public boolean updateNote(Note note) {
        Optional<Note> noteDB = noteRepository.findById(note.getId());
        if (noteDB.isPresent()) {
            noteRepository.save(note);
            return true;
        }
        return false;
    }

    @Override
    public Note deleteNoteById(Long id) {
        Optional<Note> noteDB = noteRepository.findById(id);
        if (noteDB.isPresent()) {
            Note note = noteDB.get();
            noteRepository.delete(note);
            return note;
        }
        return null;
    }
}

