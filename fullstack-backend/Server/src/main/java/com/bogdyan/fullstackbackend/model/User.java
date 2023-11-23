package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;
    private  String username;
    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    //Many to many
    @ManyToMany
    @JoinTable(
            name="user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<UGroup> ugroups = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="student_test_results",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="test_id")
    )
    private Set<Test> tests = new HashSet<>();

    public User(){

    }
    public User(String username, String login, String password, Role role){
        this.username = username;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}