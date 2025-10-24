package com.ameltaleb.TaskManager.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    // Getters and Setters
}
