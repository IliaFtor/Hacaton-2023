package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
