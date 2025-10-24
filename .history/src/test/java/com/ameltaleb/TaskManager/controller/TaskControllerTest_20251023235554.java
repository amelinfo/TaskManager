package com.ameltaleb.TaskManager.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ameltaleb.TaskManager.entity.Task;
import com.ameltaleb.TaskManager.exception.TaskNotFoundException;
import com.ameltaleb.TaskManager.module.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    

    private final UUID EXISTING_TASK_ID = UUID.fromString("5508e177-2d92-49e3-bd7b-7106d6447e38");
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
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("12345678-1234-1234-1234-123456789012"))
                .andExpect(jsonPath("$.title").value("Nueva tarea de test"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
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
    void testDeleteTask_200() throws Exception{
        mockMvc.perform(delete("/api/tasks/id",EXISTING_TASK_ID))
               .andExpect(status().isOk());
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
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(12)); //12 rows in data.sql
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
    void testGetTaskById_404() throws Exception {
        mockMvc.perform(get("/api/tasks/{id}", NON_EXISTING_TASK_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testUpdateTask() {

    }
}
