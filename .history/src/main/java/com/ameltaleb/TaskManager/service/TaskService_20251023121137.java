package com.ameltaleb.TaskManager.service;

import org.springframework.stereotype.Service;

import com.ameltaleb.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskRepository getTaskRepositoryById() {
}
