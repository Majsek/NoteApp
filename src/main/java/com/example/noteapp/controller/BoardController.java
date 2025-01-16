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
import com.example.noteapp.service.MyUserDetailsService;
import com.example.noteapp.service.NoteService;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final NoteService noteService;
    private final MyUserDetailsService userService;

    @Autowired
    public BoardController(BoardService boardService, NoteService noteService, MyUserDetailsService userService) {
        this.boardService = boardService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping({ "/boards", "/boards/" })
    public String getBoards(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        model.addAttribute("boards", boardService.getAllBoardsWithOwnerId(userDetails.getUser().getId()));
        model.addAttribute("sharedBoards", boardService.getBoardsForCollaborator(userDetails.getUser().getId()));
        return "boards";
    }

    @GetMapping("/boards/new")
    public String showNewBoardForm(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        model.addAttribute("board", new Board());
        model.addAttribute("ownerId", userDetails.getUser().getId());
        return "new-board-form";
    }

    @PostMapping("/boards/new")
    public String addNewBoard(@ModelAttribute Board board, Model model,
            @AuthenticationPrincipal MyUserDetails userDetails) {
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
        model.addAttribute("notes", noteService.findByBoardId(boardId));
        return "board-details";
    }

    @GetMapping("/boards/{id}/add-collaborator")
    public String showAddCollaboratorForm(@PathVariable Long id, Model model) {
        model.addAttribute("boardId", id);
        model.addAttribute("username", "");
        return "add-collaborator";
    }

    @PostMapping("/boards/{id}/add-collaborator")
    public String addCollaborator(
            @PathVariable Long id,
            @RequestParam String username,
            Model model) {
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "add-collaborator";
        }
        boardService.addCollaboratorToBoard(id, user);
        return "redirect:/boards/" + id;
    }

    @PostMapping("/boards/{boardId}/remove-collaborator")
    public String removeCollaborator(@PathVariable Long boardId, @RequestParam String username) {
        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId));
    
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    
        board.getCollaborators().remove(user);
    
        boardService.update(board);
    
        return "redirect:/boards/" + boardId;
    }
    
}
    
