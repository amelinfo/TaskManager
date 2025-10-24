package com.ameltaleb.TaskManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByStatus(Status status);

}
