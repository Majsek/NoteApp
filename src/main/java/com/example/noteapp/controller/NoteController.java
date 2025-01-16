package com.example.noteapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.noteapp.model.Note;
import com.example.noteapp.service.BoardService;
import com.example.noteapp.service.NoteService;

import jakarta.validation.Valid;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final BoardService boardService;

    public NoteController(NoteService noteService, BoardService boardService) {
        this.boardService = boardService;
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes";
    }

    @GetMapping("/boards/{boardId}/notes/new")
    public String createNoteForm(@PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        model.addAttribute("note", new Note());
        return "note-form";
    }

    @PostMapping("/boards/{boardId}/notes/save")
    public String saveNote(@PathVariable Long boardId, @Valid @ModelAttribute Note note,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", boardService.findById(boardId)
                    .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId)));
            model.addAttribute("notes", noteService.findByBoardId(boardId));
            model.addAttribute("boardId", boardId);
            return "note-form";
        }

        note.setBoardId(boardId);
        noteService.save(note);
        return "redirect:/boards/" + boardId;
    }

}
