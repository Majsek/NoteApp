package com.example.noteapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.Board;
import com.example.noteapp.model.User;
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
    public List<Board> getAllBoardsWithOwnerId(Long ownerId) {
        return boardRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board save(Board board) {
        if (board.getId() == null && boardRepository.existsByName(board.getName())) {
            throw new IllegalArgumentException("Board with this name already exists.");
        }
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> getBoardsForCollaborator(Long userId) {
        return boardRepository.findBoardsByCollaboratorId(userId);
    }

    public void addCollaboratorToBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.getCollaborators().add(user);
        boardRepository.save(board);
    }

    public void removeCollaborator(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId));

        if (!board.getCollaborators().contains(user)) {
            throw new IllegalArgumentException("User is not a collaborator on this board.");
        }

        board.getCollaborators().remove(user);
        boardRepository.save(board);
    }

    public Board update(Board board) {
        return boardRepository.save(board);
    }

    public boolean hasAccess(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with ID: " + boardId));

        return board.getOwnerId().equals(userId) ||
                board.getCollaborators().stream().anyMatch(collaborator -> collaborator.getId().equals(userId));
    }

    public void remove(Board board) {
        boardRepository.delete(board);
    }
}
