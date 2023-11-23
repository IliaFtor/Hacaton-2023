package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="question_groups")
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_group_id;
    private String question_group_name;

    @ManyToOne
    @JoinColumn(name="question_bank_id")
    private QuestionBank questionBank;

    @OneToMany(mappedBy = "questionGroup", cascade = CascadeType.ALL)
    private Set<Test> test = new HashSet<>();

    public QuestionGroup() {
    }
    public QuestionGroup(String question_group_name, QuestionBank questionBank) {
        this.question_group_name = question_group_name;
        this.questionBank = questionBank;
    }
}
