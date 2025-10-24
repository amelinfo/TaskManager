package com.ameltaleb.TaskManager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.exception.TaskNotFoundException;
import com.ameltaleb.TaskManager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    } 

    public Task createTask(Task task){
        Task newTask = new Task(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            LocalDateTime.now()
        );
        return taskRepository.save(newTask);
    }

    public Task getTaskById(UUID id) throws Throwable {
        return (Task) taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(UUID id, Task task) throws Throwable {
        Task existingTask = getTaskById(id);
        Task updatedTask = new Task(
            existingTask.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            existingTask.getCreatedAt()
        );

        return taskRepository.save(updatedTask);
    }

    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }


}
