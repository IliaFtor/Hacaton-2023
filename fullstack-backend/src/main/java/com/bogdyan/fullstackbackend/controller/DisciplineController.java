package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class DisciplineController {
    final private DisciplineRepository disciplineRepository;

    @Autowired
    public DisciplineController(DisciplineRepository discRepo){
        this.disciplineRepository = discRepo;
    }

    @GetMapping("/disciplines")
    public String ListDisciplines(Model model){
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }
}
