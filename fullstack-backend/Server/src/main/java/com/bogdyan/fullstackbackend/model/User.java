package com.bogdyan.fullstackbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Integer userId;

    @Column(length = 45)
    private  String username;

    @Column(length = 45)
    private String login;

    @Column(length = 45)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    //Many to many
    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name="user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "u_group_id")
    )
    private Set<UGroup> uGroups = new HashSet<>();

    @ManyToMany
    @JsonBackReference
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
