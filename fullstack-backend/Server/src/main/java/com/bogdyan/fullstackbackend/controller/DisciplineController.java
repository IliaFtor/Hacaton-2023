package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import com.bogdyan.fullstackbackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DisciplineController {
    final private DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService discServ){
        this.disciplineService = discServ;
    }

    @GetMapping("/disciplines")
    public String ListDisciplines(Model model){
        Iterable<Discipline> disciplines = disciplineService.getAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

    @PostMapping("/disciplines/add")
    public String addDisciplines(String text){
        disciplineService.createDiscipline(text);
        return "redirect:/disciplines";
    }

    @GetMapping("/disciplines/{name}")
    public String listDisciplineInfo(@PathVariable("name") String nameOfDiscipline, Model model){
        model.addAttribute(disciplineService.findByName(nameOfDiscipline));
        return "disciplineInfo";
    }
}
