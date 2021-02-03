package com.glavanize.trivia.Repository;

import com.glavanize.trivia.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>  {

}

