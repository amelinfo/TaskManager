package com.ameltaleb.TaskManager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameltaleb.TaskManager.entity.Task;

@Repository
public class TaskRepository implements JpaRepository<Task, UUID> {

    List<Task> findAllTasks();
    Optional findById(UUID id);

    boolean existsById(UUID id);

}
