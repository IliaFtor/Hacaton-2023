package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {

}
