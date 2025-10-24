package com.ameltaleb.TaskManager.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ameltaleb.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskRepository getTaskRepositoryById(UUID id) {
        return taskRepository;
    }   

    public void crea
}
