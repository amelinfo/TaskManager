package com.ameltaleb.TaskManager.service;

import java.util.List;
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

    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public TaskRepository getTaskRepositoryById(UUID id) {
        return taskRepository;
    }   

    public void createTask(Task task){
        taskRepository.save(task);
    }

    public Task updateTask(UUTask task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }


}
