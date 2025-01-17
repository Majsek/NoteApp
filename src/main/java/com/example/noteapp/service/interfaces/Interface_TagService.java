package com.example.noteapp.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.noteapp.model.Tag;

@Service
public interface Interface_TagService {

    List<Tag> getAllTags();
    
    Tag findOrCreateTag(String name);
}

