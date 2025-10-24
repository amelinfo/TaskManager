package com.ameltaleb.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoryInterface extends JpaRepository<Task, UUID> {

}
