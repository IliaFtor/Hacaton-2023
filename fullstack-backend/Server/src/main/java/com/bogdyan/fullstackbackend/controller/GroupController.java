package com.bogdyan.fullstackbackend.controller;


import com.bogdyan.fullstackbackend.model.UGroup;
import com.bogdyan.fullstackbackend.repository.GroupRepository;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class GroupController {
    final private GroupRepository groupRep;

    public GroupController(GroupRepository groupRep) {
        this.groupRep = groupRep;
    }

    @GetMapping("/groups")
    public String ListGroups(Model model){
        Iterable<UGroup> groups = groupRep.findAll();
        model.addAttribute("groups", groups);
        return "groups";
        //TODO: В html файле сделать перечисление групп
        // с перечислением студентов внутри а также указанием их роли
    }

}
