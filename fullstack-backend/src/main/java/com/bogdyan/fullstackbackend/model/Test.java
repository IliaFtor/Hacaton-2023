package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer test_id;

    private String test_name;

    @ManyToMany(mappedBy = "tests")
    private Set<User> users = new HashSet<>();
}
