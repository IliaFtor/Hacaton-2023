package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.UGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<UGroup, Integer> {
}
