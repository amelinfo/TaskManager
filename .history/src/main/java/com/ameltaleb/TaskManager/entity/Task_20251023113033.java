package com.ameltaleb.TaskManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    private Long id;
    private String title;
    private String description;

    // Getters and Setters
}
