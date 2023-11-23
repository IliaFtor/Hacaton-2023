package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
