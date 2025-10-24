package com.ameltaleb.TaskManager.entity;

import jakarta.persistence.Entity;

@Entity

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    // Getters and Setters
}
