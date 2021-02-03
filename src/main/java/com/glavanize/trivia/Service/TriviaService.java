package com.glavanize.trivia.Service;

import com.glavanize.trivia.Entity.Question;
import com.glavanize.trivia.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaService {

    @Autowired
    QuestionRepository questionRepository;

    public TriviaService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> fetchAllQuestions(Integer count) {
        List<Question> output = questionRepository.findAll();
        List<Question> finalOutput = output.stream().limit(count).collect(Collectors.toList());
        return finalOutput;
    }

}
