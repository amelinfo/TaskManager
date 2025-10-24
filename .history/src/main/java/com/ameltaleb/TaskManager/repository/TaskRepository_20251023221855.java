package com.ameltaleb.TaskManager.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ameltaleb.TaskManager.entity.Task;

public interface TaskRepository extends CrudRepository<Task, UUID> {

    
} 