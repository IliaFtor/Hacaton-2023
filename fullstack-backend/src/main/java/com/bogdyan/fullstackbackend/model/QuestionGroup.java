package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;

@Entity
@Table(name="question_groups")
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_group_id;

    private String name_qg;

    @ManyToOne
    @JoinTable(name="question_bank_id")
    private QuestionBank questionBank;
}
