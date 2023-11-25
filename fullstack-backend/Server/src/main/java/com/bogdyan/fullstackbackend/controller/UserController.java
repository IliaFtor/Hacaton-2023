package com.bogdyan.fullstackbackend.controller;


import com.bogdyan.fullstackbackend.model.Role;
import com.bogdyan.fullstackbackend.model.UGroup;
import com.bogdyan.fullstackbackend.model.User;
import com.bogdyan.fullstackbackend.repository.RoleRepository;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import com.bogdyan.fullstackbackend.service.UGroupService;
import com.bogdyan.fullstackbackend.service.RoleService;
import com.bogdyan.fullstackbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class UserController {
    private final UserService userService;
    private final UGroupService uGroupService;

    @Autowired
    public UserController(UserService userService, UGroupService uGroupService) {
        this.userService = userService;
        this.uGroupService = uGroupService;
    }


    @GetMapping("/groups-and-students")
    public String groupsAndStudents(Model model){
         //В html выводить
        Iterable<UGroup> uGroups = uGroupService.getAll();
        model.addAttribute("uGroups", uGroups);
        return "user-groups";
    }

    @PostMapping("/create-group")
    public String createGroup(String group_name){
        uGroupService.createUGroup(group_name);
        return "redirect:/groups-and-students";
    }

    @GetMapping("/getUsersByUGroupName")
    public ResponseEntity<List<User>> getUsersByUGroupName(@RequestParam String group_name) {
        List<User> users = userService.getUsersByUGroupName(group_name);
        return ResponseEntity.ok().body(users);
    }

}
