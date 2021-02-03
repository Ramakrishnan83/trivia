package com.glavanize.trivia.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trivia/questions")
public class TriviaController {
    @GetMapping("{count}")
    public  void getAllQuestions(@PathVariable Integer count) {

    }

}
