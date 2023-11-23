package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
