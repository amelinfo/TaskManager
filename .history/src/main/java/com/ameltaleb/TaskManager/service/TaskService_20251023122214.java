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

    public Task getTaskById(UUID id) throws Throwable {
        return (Task) taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(UUID id, Task task) {
        Task existingTask;
        try {
            existingTask = getTaskById(id);
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
             return taskRepository.save(existingTask);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public void deleteTask(UUID id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }


}
