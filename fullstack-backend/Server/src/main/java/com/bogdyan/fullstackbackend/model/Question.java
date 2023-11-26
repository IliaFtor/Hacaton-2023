package com.bogdyan.fullstackbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="questions")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "questions")
    private Set<QuestionGroup> questionGroups = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "questions")
    private Set<Test> tests = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="question_bank_id")
    private QuestionBank questionBank;

    public Question() {
    }
    public Question(String questionText) {
        this.questionText = questionText;
    }

}
