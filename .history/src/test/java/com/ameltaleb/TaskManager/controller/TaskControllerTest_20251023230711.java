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
    void testCreateTask() {

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
        mockmvc.perform(get("/api/task"))
               .andExpect(status().isNotFound())
               .andExpect
    }

    @Test
    void testGetTaskById() {

    }

    @Test
    void testUpdateTask() {

    }
}
