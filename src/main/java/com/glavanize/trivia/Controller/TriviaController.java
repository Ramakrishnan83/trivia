package com.glavanize.trivia.Controller;

import com.glavanize.trivia.Entity.Question;
import com.glavanize.trivia.Service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trivia/questions")
public class TriviaController {

    @Autowired
    TriviaService triviaService;

    @GetMapping("{count}")
    public  List<Question> getAllQuestions(@PathVariable Integer count) {
        return triviaService.fetchAllQuestions(count);
    }

}
