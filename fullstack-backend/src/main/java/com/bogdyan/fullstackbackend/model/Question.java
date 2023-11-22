package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="questions")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_id;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

}
