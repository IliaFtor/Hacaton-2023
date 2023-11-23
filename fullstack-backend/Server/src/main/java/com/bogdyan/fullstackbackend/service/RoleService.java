package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Role;
import com.bogdyan.fullstackbackend.repository.RoleRepository;
import com.bogdyan.fullstackbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findById(int roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
    }


}
