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
@Table(name="ugroups")
public class UGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uGroupId;
    private String uGroupName;

    @ManyToMany(mappedBy="uGroups")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy="uGroups")
    @JsonBackReference
    private Set<Discipline> disciplines = new HashSet<>();

    public UGroup(String uGroupName) {
        this.uGroupName = uGroupName;
    }
    public UGroup() {
    }

}
