package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student_groups")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_group_id;
    private String name;

    @ManyToMany(mappedBy="groups")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy="groups")
    private Set<Discipline> disciplines = new HashSet<>();

    public Integer getStudent_group_id() {
        return student_group_id;
    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public StudentGroup() {
    }

    public void setStudent_group_id(Integer student_group_id) {
        this.student_group_id = student_group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
