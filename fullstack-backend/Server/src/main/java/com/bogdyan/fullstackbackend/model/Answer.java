package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;
    private Byte isCorrect;
    private Integer answerScore;

    @Column(length = 45)
    private String content;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    public Answer() {
    }
    public Answer(Byte isCorrect, Integer answerScore, Question question, String content) {
        this.isCorrect = isCorrect;
        this.answerScore = answerScore;
        this.question = question;
        this.content = content;
    }

}
