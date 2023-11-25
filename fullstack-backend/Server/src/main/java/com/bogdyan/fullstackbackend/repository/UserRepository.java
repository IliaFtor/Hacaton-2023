package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Integer> {

    List<User> findAllByuGroups_uGroupId(Integer group_id);

    List<User> findAllByuGroups_uGroupName(String group_name);
}
