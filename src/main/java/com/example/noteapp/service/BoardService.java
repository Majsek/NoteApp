package com.example.noteapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.Board;
import com.example.noteapp.repository.Interface_BoardRepository;
import com.example.noteapp.service.interfaces.Interface_BoardService;

@Service
public class BoardService implements Interface_BoardService {

    private final Interface_BoardRepository boardRepository;

    @Autowired
    public BoardService(Interface_BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board save(Board board) {
        if (boardRepository.existsByName(board.getName())) {
            throw new IllegalArgumentException("Board with this name already exists.");
        }
        return boardRepository.save(board);
    }
}
