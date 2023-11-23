package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.QuestionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Integer> {
}
