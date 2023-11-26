package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.Role;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository){
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getAll(){
        return disciplineRepository.findAll();
    }

    public Discipline createDiscipline(String name){
        Discipline discipline = new Discipline(name);
        disciplineRepository.save(discipline);
        return discipline;
    }
    public Discipline findById(int disciplineId) {
        return disciplineRepository.findById(disciplineId).orElseThrow(() -> new NoSuchElementException("Role not found"));
    }
    public Discipline findByName(String name) {
        return disciplineRepository.findByDisciplineName(name);
    }
}
