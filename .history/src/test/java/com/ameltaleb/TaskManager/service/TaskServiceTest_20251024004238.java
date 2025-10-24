package com.ameltaleb.TaskManager.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ameltaleb.TaskManager.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskRepository taskRepository;

    private final UUID EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e38");
    private final UUID NON_EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e99");

    
    @Test
    void testCreateTask() {
        Task newTask = new Task(UUID.randomUUID(), "new Task", "new Description", Status.)
    }

    @Test
    void testDeleteTask() {

    }

    @Test
    void testGetAllTasks() {

    }

    @Test
    void testGetTaskById() {

    }

    @Test
    void testUpdateTask() {

    }
}
