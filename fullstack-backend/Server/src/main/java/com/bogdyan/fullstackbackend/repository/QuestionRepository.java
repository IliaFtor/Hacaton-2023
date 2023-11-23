package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
