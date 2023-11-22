package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Integer user_id;
    private  String username;
    private String login;
    private int role;

    //Many to many
    @ManyToMany
    @JoinTable(
            name="user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "student_group_id")
    )
    private Set<StudentGroup> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="student_test_result",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="test_id")
    )
    private Set<Test> tests = new HashSet<>();

    public User(){

    }
    public User(String username, String login, int role){
        this.username = username;
        this.login = login;
        this.role = role;
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRole() {
        return role;
    }

    public void setEmail(int role) {
        this.role = role;
    }


}
