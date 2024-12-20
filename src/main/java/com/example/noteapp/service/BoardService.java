package com.example.noteapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.Board;
import com.example.noteapp.repository.Interface_BoardRepository;

@Service
public class BoardService {

    private final Interface_BoardRepository boardRepository;

    @Autowired
    public BoardService(Interface_BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board save(Board board) {
        if (boardRepository.existsByName(board.getName())) {
            throw new IllegalArgumentException("Board with this name already exists.");
        }
        return boardRepository.save(board);
    }
}
