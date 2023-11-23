package com.bogdyan.fullstackbackend.controller;


import com.bogdyan.fullstackbackend.model.Role;
import com.bogdyan.fullstackbackend.model.User;
import com.bogdyan.fullstackbackend.repository.RoleRepository;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import com.bogdyan.fullstackbackend.service.RoleService;
import com.bogdyan.fullstackbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String groupStudents(Model model){
        List<User> users = userService.getUsersByUGroupId(1);
        userService.findById(2);
        userService.createUser("Anatoliy", "moyak33", "BAra7306", roleService.findById(1));
        return "index";
    }

}
