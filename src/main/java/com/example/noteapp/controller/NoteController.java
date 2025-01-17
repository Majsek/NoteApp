package com.example.noteapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.noteapp.model.Note;
import com.example.noteapp.model.Tag;
import com.example.noteapp.service.BoardService;
import com.example.noteapp.service.NoteService;
import com.example.noteapp.service.TagService;

import jakarta.validation.Valid;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final BoardService boardService;
    private final TagService tagService;

    public NoteController(NoteService noteService, BoardService boardService, TagService tagService) {
        this.boardService = boardService;
        this.noteService = noteService;
        this.tagService = tagService;

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
    public String saveNote(@PathVariable Long boardId, 
                           @Valid @ModelAttribute Note note, 
                           BindingResult bindingResult, 
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", boardService.findById(boardId)
                    .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId)));
            model.addAttribute("notes", noteService.findByBoardId(boardId));
            model.addAttribute("boardId", boardId);
            return "note-form";
        }
    
        // Převod tagsInput na Set<Tag>
        String[] tagNames = note.getTagsInput().split(",");
        Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tagNames) {
            String trimmedTagName = tagName.trim();
            if (!trimmedTagName.isEmpty()) {
                tagSet.add(tagService.findOrCreateTag(trimmedTagName));
            }
        }
    
        note.setTags(tagSet);
        note.setBoardId(boardId);
        noteService.save(note);
    
        return "redirect:/boards/" + boardId;
    }
    

    @PostMapping("/boards/{boardId}/notes/remove")
    public String removeNote(@PathVariable Long boardId,
            @RequestParam Long noteId) {
        Note note = noteService.getNoteById(noteId);

        if (note == null || !note.getBoardId().equals(boardId)) {
            throw new IllegalArgumentException("Invalid note or note does not belong to the specified board.");
        }

        noteService.delete(noteId);

        return "redirect:/boards/" + boardId;
    }
}
