package com.glavanize.trivia.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quiz_id;
    private Long question_number;
    private String question;
    private Date created_at;
    @OneToMany
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    private List<Answer> answerList;
}
