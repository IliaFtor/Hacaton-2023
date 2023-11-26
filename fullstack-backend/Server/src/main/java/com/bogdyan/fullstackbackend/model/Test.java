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
    private Integer testId;

    @Column(length = 45)
    private String testName;

    @ManyToMany(mappedBy = "tests")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="question_group_id")
    private QuestionGroup questionGroup;

    @ManyToMany
    @JoinTable(
            name="test_questions",
            joinColumns=@JoinColumn(name="test_id"),
            inverseJoinColumns = @JoinColumn(name="question_id")
    )
    private Set<Question> questions = new HashSet<>();

    public Test(String testName, QuestionGroup questionGroup) {
        this.testName = testName;
        this.questionGroup = questionGroup;
    }
    public Test() {
    }

}
