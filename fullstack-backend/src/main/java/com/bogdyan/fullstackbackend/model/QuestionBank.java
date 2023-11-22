package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questionbanks")
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_bank_id;

    private String name_qb;

    @ManyToOne
    @JoinColumn(name="discipline_id")
    private Discipline discipline_id;

    @OneToMany(mappedBy = "questionBank", cascade = CascadeType.ALL)
    private Set<QuestionGroup> questionGroups = new HashSet<>();
}
