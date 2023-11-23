package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Role;
import com.bogdyan.fullstackbackend.model.User;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersByUGroupId(Integer group_id) {
        return userRepository.findAllByugroups_groupId(group_id);
    }

    public List<User> getUsersByUGroupName(String group_name) {
        return userRepository.findAllByugroups_groupName(group_name);
    }

    public User findById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(String username, String login, String password, Role role) {
        User newUser = new User(username, login, password, role);
        userRepository.save(newUser);
        return newUser;
    }
}

