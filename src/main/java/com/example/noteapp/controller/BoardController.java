package com.example.noteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.noteapp.model.Board;
import com.example.noteapp.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public String getBoards(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "boards";
    }

    @GetMapping("/boards/new")
    public String showNewBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "new-board";
    }

    @PostMapping("/boards/new")
    public String addNewBoard(@ModelAttribute Board board, Model model) {
        try {
            boardService.save(board);
            return "redirect:/boards";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "new-board";
        }
    }
}
