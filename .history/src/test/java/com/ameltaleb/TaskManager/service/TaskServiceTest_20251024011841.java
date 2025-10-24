package com.ameltaleb.TaskManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.exception.GlobalExceptionHandler;
import com.ameltaleb.TaskManager.exception.TaskNotFoundException;
import com.ameltaleb.TaskManager.module.Status;
import com.ameltaleb.TaskManager.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    private final UUID EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e40");
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
    void testCreateTask_ko() {
        Task newTask = new Task("", "", Status.IN_PROGRESS);
        Task savedTask = new Task(EXISTING_TASK_ID, "", "", Status.IN_PROGRESS);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result =  taskService.createTask(newTask);
    }

    @Test
    void testDeleteTask_ok() {
        // Given
        when(taskRepository.existsById(EXISTING_TASK_ID)).thenReturn(true);

        // When
        taskService.deleteTask(EXISTING_TASK_ID);

        // Then
        verify(taskRepository, times(1)).existsById(EXISTING_TASK_ID);
        verify(taskRepository, times(1)).deleteById(EXISTING_TASK_ID);
    }
    
    @Test
    void testDeleteTask_ko(){
                // Given
        when(taskRepository.existsById(NON_EXISTING_TASK_ID)).thenReturn(false);

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(NON_EXISTING_TASK_ID));
        verify(taskRepository, times(1)).existsById(NON_EXISTING_TASK_ID);
        verify(taskRepository, never()).deleteById(any(UUID.class));
    }
    @Test
    void testGetAllTasks() {
        // Given
        List<Task> expectedTasks = List.of(
            new Task("Task 1", "Description 1", Status.PENDING),
            new Task("Task 2", "Description 2", Status.COMPLETED)
        );
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        // When
        List<Task> result = taskService.getAllTasks();

        // Then
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById_ok() throws Throwable {
        // Given
        Task expectedTask = new Task(EXISTING_TASK_ID, "Test Task", "Test Description", Status.PENDING, null);
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(expectedTask));

        // When
        Task result = taskService.getTaskById(EXISTING_TASK_ID);

        // Then
        assertNotNull(result);
        assertEquals(EXISTING_TASK_ID, result.getId());
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
    }

    @Test
    void testGetTaskById_ko() throws Throwable {
        // Given
        when(taskRepository.findById(NON_EXISTING_TASK_ID)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(NON_EXISTING_TASK_ID));
        verify(taskRepository, times(1)).findById(NON_EXISTING_TASK_ID);
    }

    @Test
    void testUpdateTask_ok() throws Throwable {
        // Given
        Task existingTask = new Task(EXISTING_TASK_ID, "Old Title", "Old Description", Status.PENDING, null);
        Task updatedDetails = new Task(EXISTING_TASK_ID, "New Title", "New Description", Status.COMPLETED, null);
        
        when(taskRepository.findById(EXISTING_TASK_ID)).thenReturn(Optional.of(existingTask));
        
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task taskToSave = invocation.getArgument(0);
            return new Task(
                taskToSave.getId(),
                taskToSave.getTitle(),
                taskToSave.getDescription(),
                taskToSave.getStatus(),
                taskToSave.getCreatedAt()
            );
        });

        // When
        Task result = taskService.updateTask(EXISTING_TASK_ID, updatedDetails);

        // Then
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals(Status.COMPLETED, result.getStatus());
        
        verify(taskRepository, times(1)).findById(EXISTING_TASK_ID);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdateTask_ko(){
        // Given
        Task updatedDetails = new Task(NON_EXISTING_TASK_ID, "New Title", "New Description", Status.COMPLETED, null);
        when(taskRepository.findById(NON_EXISTING_TASK_ID)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(NON_EXISTING_TASK_ID, updatedDetails));
        verify(taskRepository, times(1)).findById(NON_EXISTING_TASK_ID);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
