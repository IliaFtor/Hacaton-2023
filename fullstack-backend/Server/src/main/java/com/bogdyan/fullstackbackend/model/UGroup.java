package com.bogdyan.fullstackbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="ugroups")
public class UGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;
    private String groupName;

    @ManyToMany(mappedBy="ugroups")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy="ugroups")
    private Set<Discipline> disciplines = new HashSet<>();

    public UGroup(String groupName) {
        this.groupName = groupName;
    }
    public UGroup() {
    }

}
