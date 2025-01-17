package com.example.noteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.noteapp.model.Tag;

@Repository
public interface Interface_TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
    boolean existsByName(String name);
}
