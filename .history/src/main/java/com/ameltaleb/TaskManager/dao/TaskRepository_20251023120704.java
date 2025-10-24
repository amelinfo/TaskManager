package com.ameltaleb.TaskManager.dao;

import java.util.List;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.module.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List
    List<Task> findByStatus(Status status);

}
