package com.ameltaleb.TaskManager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    @NotBlank(message = "Status is mandatory")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Task() {
    }

    public Task(UUID id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
    public UUID getId() {
        return id;
    }   
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
}
