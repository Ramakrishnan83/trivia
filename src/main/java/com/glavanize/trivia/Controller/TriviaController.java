package com.glavanize.trivia.Controller;

import com.glavanize.trivia.Entity.Question;
import com.glavanize.trivia.Service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(@RequestBody Question question){
        return triviaService.addQuestion(question);
    }

}
