package com.ameltaleb.TaskManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", upda)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    // Getters and Setters
}
