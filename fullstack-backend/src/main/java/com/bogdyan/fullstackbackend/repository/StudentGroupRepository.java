package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
}
