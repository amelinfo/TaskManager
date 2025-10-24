package com.ameltaleb.TaskManager.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameltaleb.TaskManager.entity.Task;

public interface TaskRepositoryInterface extends JpaRepository<Task, UUID> {

    List<Task> findAllTasks();
    Optional<Task> findTaskById(UUID id);
    Task saveTask(Task task);
    void deleteTaskById(UUID id);
    
}
