package com.ameltaleb.TaskManager.dto;

import org.hibernate.validator.constraints.UUID;

import com.ameltaleb.TaskManager.module.Status;

public record TaskResponse(
        UUID id, 
        String title, 
        String description, 
        Status status, 
        LocalDateTime createdAt) {
}