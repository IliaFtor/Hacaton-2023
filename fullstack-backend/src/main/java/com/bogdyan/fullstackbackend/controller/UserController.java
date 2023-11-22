package com.bogdyan.fullstackbackend.controller;


import com.bogdyan.fullstackbackend.model.User;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @PostMapping("/groups")
//    User newUser(@RequestBody User newUser){
//
//
//    }

}
