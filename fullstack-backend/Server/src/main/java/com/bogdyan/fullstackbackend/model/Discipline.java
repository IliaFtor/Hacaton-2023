package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discipline_id;
    private String discipline_name;

    @ManyToMany
    @JoinTable(
            name="ugroup_didciplines",
            joinColumns = @JoinColumn(name="discipline_id"),
            inverseJoinColumns = @JoinColumn(name="u_group_id")
    )
    private Set<UGroup> uGroups = new HashSet<>();

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private Set<QuestionBank> questionBanks = new HashSet<>();

    public Discipline() {
    }
}
