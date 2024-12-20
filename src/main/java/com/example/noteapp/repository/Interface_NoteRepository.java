package com.example.noteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.noteapp.model.Note;

@Repository
public interface Interface_NoteRepository extends JpaRepository<Note, Long> {
}
