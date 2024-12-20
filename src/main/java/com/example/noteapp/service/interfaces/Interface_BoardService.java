package com.example.noteapp.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.noteapp.model.Board;

@Service
public interface Interface_BoardService {
    public List<Board> getAllBoards();

    public Board save(Board board);

    public Optional<Board> findById(Long id);
}
