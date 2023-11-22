package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answer_id;

    @ManyToOne
    @JoinTable(name="question_id")
    private Question question;

}
