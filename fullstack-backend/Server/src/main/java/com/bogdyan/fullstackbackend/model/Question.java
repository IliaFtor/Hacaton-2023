package com.bogdyan.fullstackbackend.model;

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
    private Integer question_id;
    private String question_text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="question_group_id")
    private QuestionGroup questionGroup;

    public Question() {
    }
    public Question(String question_text, QuestionGroup questionGroup) {
        this.question_text = question_text;
        this.questionGroup = questionGroup;
    }

}
