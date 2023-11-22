package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discipline_id;
    private String name;

    @ManyToMany
    @JoinTable(
            name="group_didciplines",
            joinColumns = @JoinColumn(name="discipline_id"),
            inverseJoinColumns = @JoinColumn(name="student_group_id")
    )
    private Set<StudentGroup> groups = new HashSet<>();

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private Set<QuestionBank> questionBanks = new HashSet<>();


}
