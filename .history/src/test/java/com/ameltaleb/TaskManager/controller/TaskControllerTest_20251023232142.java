package com.ameltaleb.TaskManager.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    void testCreateTask_201() {

    }

    @Test
    void testDeleteTask() {

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
