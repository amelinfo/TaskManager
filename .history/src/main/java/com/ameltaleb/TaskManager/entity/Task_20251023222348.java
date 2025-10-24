package com.ameltaleb.TaskManager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.ameltaleb.TaskManager.module.Status;

@Entity
@Table(name = "tasks")
public class Task {

    
    @Id
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TaskStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // Constructor por defecto (OBLIGATORIO para JPA)
    public Task() {
    }
}
