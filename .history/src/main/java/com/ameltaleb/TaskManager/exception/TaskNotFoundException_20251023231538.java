package com.ameltaleb.TaskManager.exception;

public class TaskNotFoundException extends RuntimeException {

        public TaskNotFoundException(UUID id) {
        super("Task not found with id: " + id);
    }
}
