package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer test_id;
    private String test_name;

    @ManyToMany(mappedBy = "tests")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="question_group_id")
    private QuestionGroup questionGroup;

    public Test(String test_name, QuestionGroup questionGroup) {
        this.test_name = test_name;
        this.questionGroup = questionGroup;
    }
    public Test() {
    }

}
