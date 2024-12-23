package com.example.noteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.noteapp.model.Board;

@Repository
public interface Interface_BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByName(String name);

    // find all with owner id
    List<Board> findByOwnerId(Long ownerId);
}
