package com.ameltaleb.TaskManager.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<

    public TaskRepository getTaskRepositoryById(UUID id) {
        return taskRepository;
    }   

    public void createTask(Task task){
        taskRepository.save(task);
    }


}
