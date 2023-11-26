package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    Discipline findByDisciplineName(String disciplineName);
}
