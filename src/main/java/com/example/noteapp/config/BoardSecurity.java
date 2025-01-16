package com.example.noteapp.config;

import org.springframework.stereotype.Component;

import com.example.noteapp.service.BoardService;

@Component
public class BoardSecurity {

    private final BoardService boardService;

    public BoardSecurity(BoardService boardService) {
        this.boardService = boardService;
    }

    public boolean hasAccess(Long boardId, Long userId) {
        return boardService.hasAccess(boardId, userId);
    }
}
