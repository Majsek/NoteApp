package com.example.noteapp.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "Title cannot exceed 255 characters.")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 3000, message = "Description cannot exceed 3000 characters.")
    @Column(length = 3000)
    private String description;

    @Column(nullable = false)
    private Long ownerId;

    @ManyToMany
    @JoinTable(name = "board_collaborators", joinColumns = @JoinColumn(name = "board_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> collaborators;

    // Constructors

    public Board() {
    }

    public Board(String name, String description, Long ownerId) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

    public Set<User> getCollaborators() {
        return collaborators;
    }
    
    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
