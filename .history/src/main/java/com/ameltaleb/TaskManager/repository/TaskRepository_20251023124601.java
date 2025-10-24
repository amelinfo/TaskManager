package com.ameltaleb.TaskManager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.ameltaleb.TaskManager.entity.Task;

@Repository
public class TaskRepository implements TaskRepositoryInterface {

    private final TaskRepositoryInterface taskRepositoryInterface;

    public TaskRepository(TaskRepositoryInterface taskRepositoryInterface) {
        this.taskRepositoryInterface = taskRepositoryInterface;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepositoryInterface.findAll();
    }

    @Override
    public Optional<Task> findTaskById(UUID id) {
        return taskRepositoryInterface.findById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepositoryInterface.save(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepositoryInterface.deleteById(id);
    }

}
