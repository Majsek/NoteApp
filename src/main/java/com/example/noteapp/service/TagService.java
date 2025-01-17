package com.example.noteapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.noteapp.model.Tag;
import com.example.noteapp.repository.Interface_TagRepository;
import com.example.noteapp.service.interfaces.Interface_TagService;

@Service
public class TagService implements Interface_TagService {

    private final Interface_TagRepository tagRepository;

    @Autowired
    public TagService(Interface_TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findOrCreateTag(String name) {
        String trimmedName = name.trim();
        System.out.println("Looking for tag: " + trimmedName);
        if (trimmedName.isEmpty()) {
            System.out.println("Tag name is empty, skipping.");
            return null;
        }
    
        Tag tag = tagRepository.findByName(trimmedName);
        if (tag != null) {
            System.out.println("Found existing tag: " + tag);
            return tag;
        }
    
        System.out.println("Creating new tag: " + trimmedName);
        tag = new Tag(trimmedName);
        tagRepository.save(tag);
        System.out.println("New tag saved: " + tag);
    
        return tag;
    }
    
}
