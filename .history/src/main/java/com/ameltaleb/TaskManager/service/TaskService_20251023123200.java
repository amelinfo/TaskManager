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

    public void createTask(Task task){
        Task newTask = new Task(
            task.id(),
            task.title(),
            task.description(),
            task.status(),
            null
        );
        taskRepository.save(newTask);
    }

    public Task getTaskById(UUID id) throws Throwable {
        return (Task) taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(UUID id, Task task) throws Throwable {
        Task existingTask = getTaskById(id);
        Task updatedTask = new Task(
            existingTask.id(),
            task.title(),
            task.description(),
            task.status(),
            existingTask.createdAt()
        );

        return taskRepository.save(updatedTask);
    }

    public void deleteTask(UUID id) {
        taskRepository.findById(id)
            .ifPresentOrElse(
                task -> taskRepository.deleteById(id),
                () -> { throw new RuntimeException("Task not found with id: " + id); }
            );
    }


}
