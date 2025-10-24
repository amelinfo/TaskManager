package com.ameltaleb.TaskManager.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.module.Status;
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
        Task newTask = new Task(UUID.randomUUID(), "new Task", "new Description", Status.IN_PROGRESS);
        Task savedTask = new Task(EXISTING_TASK_ID, "custom title", "custom description", Status.IN_PROGRESS);

        when(taskRepository.save(any(Task.class))).thenReturn
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
