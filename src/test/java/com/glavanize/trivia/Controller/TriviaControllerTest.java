package com.glavanize.trivia.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.glavanize.trivia.Entity.Answer;
import com.glavanize.trivia.Entity.Question;
import com.glavanize.trivia.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    ObjectMapper objectMapper;

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
        Answer ans1 = Answer.builder()
                .question_id(1L)
                .choice("A")
                .text("Messi")
                .correct(true).build();
        Answer ans2 = Answer.builder()
                .question_id(1L)
                .choice("B")
                .text("Ronaldo")
                .correct(false).build();
        Answer ans3 = Answer.builder()
                .question_id(1L)
                .choice("C")
                .text("Mbaape")
                .correct(false).build();
        List<Answer> answerList = new ArrayList<>();
        answerList.add(ans1);
        answerList.add(ans2);
        answerList.add(ans3);
        Question question = Question.builder()
                .quiz_id(10L)
                .question_number(1L)
                .question("Who is the greatest socceer player of all time?")
                .created_at(new Date())
                .answerList(answerList)
                .build();
        mockMvc.perform(post("/api/v1/trivia/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.quiz_id").value(10L));
    }
}