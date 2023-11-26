package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
