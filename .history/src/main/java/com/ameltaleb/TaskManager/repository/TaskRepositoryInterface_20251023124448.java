package com.ameltaleb.TaskManager.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameltaleb.TaskManager.entity.Task;

public interface TaskRepositoryInterface extends JpaRepository<Task, UUID> {

    
}
