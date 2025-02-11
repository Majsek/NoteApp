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
    public boolean updateNote(Note note) {
        if (note.getId() == null) {
            throw new IllegalArgumentException("Note ID must not be null for update.");
        }
    
        Note existingNote = noteRepository.findById(note.getId())
                .orElseThrow(() -> new IllegalArgumentException("Note not found."));
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        existingNote.setTags(note.getTags());
        noteRepository.save(existingNote);
    
        return true;
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

    @Override
    public Note save(Note note) {
        System.out.println("Saving note: " + note);
        try {
            Note savedNote = noteRepository.save(note);
            System.out.println("Note saved: " + savedNote);
            return savedNote;
        } catch (Exception e) {
            System.out.println("Error saving note: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Object findByBoardId(Long id) {
        return noteRepository.findByBoardId(id);
    }

    public void delete(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with ID: " + noteId));
        noteRepository.delete(note);
    }

}
