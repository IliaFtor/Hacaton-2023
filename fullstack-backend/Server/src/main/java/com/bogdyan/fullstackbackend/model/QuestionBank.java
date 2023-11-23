package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "question_banks")
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_bank_id;
    private String question_bank_name;

    @ManyToOne
    @JoinColumn(name="discipline_id")
    private Discipline discipline;

    @OneToMany(mappedBy = "questionBank", cascade = CascadeType.ALL)
    private Set<QuestionGroup> questionGroups = new HashSet<>();

    public QuestionBank() {
    }
    public QuestionBank(String question_bank_name, Discipline discipline) {
        this.question_bank_name = question_bank_name;
        this.discipline = discipline;
    }
}
