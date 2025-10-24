package com.ameltaleb.TaskManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    // Getters and Setters
}
