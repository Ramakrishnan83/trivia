package com.glavanize.trivia;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TriviaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getSingleQuestion() throws Exception {
        mockMvc.perform(get("/api/v1/trivia/questions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                 .andExpect(jsonPath("[1].id").doesNotExist());

    }
}