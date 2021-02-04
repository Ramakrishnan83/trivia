package com.glavanize.trivia.Controller;


import com.glavanize.trivia.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
public class TriviaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void getNoQuestions() throws Exception {
        questionRepository.deleteAll();
        mockMvc.perform(get("/api/v1/trivia/questions/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").doesNotExist());

    }

    @Test
    public void getQuestions() throws Exception {
        mockMvc.perform(get("/api/v1/trivia/questions/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[1].id").exists())
                .andExpect(jsonPath("$[0].answerList[0]").exists())
                .andExpect(jsonPath("$[0].answerList[1]").exists())
                .andExpect(jsonPath("$[0].answerList[2]").exists())
                .andExpect(jsonPath("$[0].answerList[3]").doesNotExist());;

    }

    @Test
    public void addQuestion() throws Exception {
        mockMvc.perform(post("/api/v1/trivia/questions"))
                .andExpect(status().isOk());
    }
}