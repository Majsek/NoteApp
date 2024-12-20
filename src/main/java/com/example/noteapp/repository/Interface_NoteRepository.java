package com.example.noteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.noteapp.model.Note;

@Repository
public interface Interface_NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByBoardId(Long boardId);
}
