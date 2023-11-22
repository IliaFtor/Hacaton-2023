package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {

}
