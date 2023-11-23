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
    private Integer answer_id;
    private Byte is_correct;
    private Integer answer_score;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    public Answer() {
    }
    public Answer(Byte is_correct, Integer answer_score, Question question) {
        this.is_correct = is_correct;
        this.answer_score = answer_score;
        this.question = question;
    }

}
