package com.ameltaleb.TaskManager.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.exception.TaskNotFoundException;
import com.ameltaleb.TaskManager.module.Status;
import com.ameltaleb.TaskManager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    private final UUID EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e49");
    private final UUID NON_EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e99");

    @Test
    void testCreateTask_201() throws Exception {
        Task newTask = new Task(
            UUID.fromString("12345678-1234-1234-1234-123456789012"),
            "Nueva tarea de test",
            "Descripción de prueba",
            Status.IN_PROGRESS,
            null
        );

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateTask_400() throws Exception {
            Task newTask = new Task(
            UUID.fromString("12345678-1234-1234-1234-14553229012"),
            "",
            "Descripción de prueba",
            Status.IN_PROGRESS,
            null
        );
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isBadRequest());  
    }

    @Test
    void testDeleteTask_204() throws Exception{
        // Given
        doNothing().when(taskService).deleteTask(EXISTING_TASK_ID);

        // When & Then
        mockMvc.perform(delete("/api/tasks/{id}", EXISTING_TASK_ID))
                .andExpect(status().isNoContent());

        verify(taskService, times(1)).deleteTask(EXISTING_TASK_ID);
    }

    @Test
    void testDeleteTask_404() throws Exception {
        // Given
        doThrow(new TaskNotFoundException(NON_EXISTING_TASK_ID))
                .when(taskService).deleteTask(NON_EXISTING_TASK_ID);

        // When & Then
        mockMvc.perform(delete("/api/tasks/{id}", NON_EXISTING_TASK_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Task not found with id: " + NON_EXISTING_TASK_ID));

        verify(taskService, times(1)).deleteTask(NON_EXISTING_TASK_ID);
    }

    @Test
    void testGetAllTasks_200() throws Exception {
        // Given
        when(taskService.getAllTasks()).thenReturn(java.util.List.of(
            new Task(UUID.fromString("a81bc81b-dead-4e5d-abff-90865d1e13b1"),"Task 1", "Desc 1", Status.PENDING),
            new Task(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),"Task 2", "Desc 2", Status.COMPLETED)
        ));

        // When & Then
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    void testGetAllTasks_404() throws Exception{
        mockMvc.perform(get("/api/task"))  //wrong endpoint
               .andExpect(status().isNotFound());
    }

    @Test
    void testGetTaskById_200() throws Exception {
        mockMvc.perform(get("/api/tasks/{id}", EXISTING_TASK_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTaskById_404() throws Throwable {
        // Given
        when(taskService.getTaskById(NON_EXISTING_TASK_ID))
                .thenThrow(new TaskNotFoundException(NON_EXISTING_TASK_ID));

        // When & Then
        mockMvc.perform(get("/api/tasks/{id}", NON_EXISTING_TASK_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Task not found with id: " + NON_EXISTING_TASK_ID));
    }

    @Test
    void testUpdateTask_200() throws Throwable {
        Task taskToUpdate = new Task(EXISTING_TASK_ID,"task to update", "Task to be updated successfully", Status.IN_PROGRESS);
        // Given
        when(taskService.updateTask(eq(EXISTING_TASK_ID), any(Task.class))).thenReturn(taskToUpdate);

        // When & Then
        mockMvc.perform(put("/api/tasks/{id}", EXISTING_TASK_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskToUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("task to update"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void testUpdateTask_404() throws Throwable {
        Task taskToUpdate = new Task(NON_EXISTING_TASK_ID,"task to update", "Task not to be updated successfully", Status.IN_PROGRESS);

        //given
        when(taskService.updateTask(eq(NON_EXISTING_TASK_ID), any(Task.class))).thenThrow()

        //when and then

        mockMvc.perform(put("/api/tasks/{id}", NON_EXISTING_TASK_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
