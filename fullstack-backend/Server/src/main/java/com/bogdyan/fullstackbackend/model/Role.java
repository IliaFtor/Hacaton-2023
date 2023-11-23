package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;
    private String role_name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Role() {
    }
    public Role(String role_name) {
        this.role_name = role_name;
    }
}
