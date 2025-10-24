package com.ameltaleb.TaskManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    private final UUID EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e38");
    private final UUID NON_EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e99");

    
    @Test
    void testCreateTask() {
        Task newTask = new Task("new Task", "new Description", Status.IN_PROGRESS);
        Task savedTask = new Task(EXISTING_TASK_ID, "custom title", "custom description", Status.IN_PROGRESS);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(newTask);

        assertNotNull(result);
        assertEquals(EXISTING_TASK_ID, result.getId());
        verify(taskRepository, times(1)).save(any(Task.class));

    }

    @Test
    void testDeleteTask() {

    }

    @Test
    void testGetAllTasks() {

    }

    @Test
    void testGetTaskById() {
        // Given
        Task expectedTask = new Task(EXISTING_TASK_ID, "Test Task", "Test Description", TaskStatus.PENDING, null);
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(expectedTask));

        // When
        Task result = taskService.getTaskById(EXISTING_TASK_ID);

        // Then
        assertNotNull(result);
        assertEquals(EXISTING_TASK_ID, result.getId());
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
    }

    @Test
    void testUpdateTask() {

    }
}
