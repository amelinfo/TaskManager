package com.ameltaleb.TaskManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository implements JpaRepository<Task, UUID> {

}
