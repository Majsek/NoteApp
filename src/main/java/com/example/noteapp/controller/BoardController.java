package com.example.noteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.noteapp.config.MyUserDetails;
import com.example.noteapp.model.Board;
import com.example.noteapp.model.User;
import com.example.noteapp.service.BoardService;
import com.example.noteapp.service.NoteService;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final NoteService noteService;

    @Autowired
    public BoardController(BoardService boardService, NoteService noteService) {
        this.boardService = boardService;
        this.noteService = noteService;
    }

    @GetMapping({"/boards", "/boards/"})
    public String getBoards(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        model.addAttribute("boards", boardService.getAllBoardsWithOwnerId(userDetails.getUser().getId()));
        return "boards";
    }

    @GetMapping("/boards/new")
    public String showNewBoardForm(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        model.addAttribute("board", new Board());
        model.addAttribute("ownerId", userDetails.getUser().getId());
        return "new-board-form";
    }

    @PostMapping("/boards/new")
    public String addNewBoard(@ModelAttribute Board board, Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        try {
            board.setOwnerId(userDetails.getUser().getId());
            boardService.save(board);
            return "redirect:/boards";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "new-board-form";
        }
    }

    @GetMapping("/boards/{boardId}")
    public String getBoardDetails(@PathVariable Long boardId, Model model) {
        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId));
        model.addAttribute("board", board);
        model.addAttribute("notes", noteService.findByBoardId(boardId)); // Přidání poznámek
        return "board-details";
    }
}
