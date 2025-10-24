package com.ameltaleb.TaskManager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.ameltaleb.TaskManager.module.Status;

@Entity
@Table(name = "tasks")
public record Task(
    @Id
    @Generated(value = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id,

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    String title,

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(name = "description", length = 500)
    String description,

    @NotBlank(message = "Status is mandatory")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    @Column(name = "status", nullable = false, length = 20)
    Status status,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt
) {
    public Task(UUID id, String title, String description, Status status) {
        this(id, title, description, status, null);
    }
}
