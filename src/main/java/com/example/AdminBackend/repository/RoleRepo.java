package com.example.AdminBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AdminBackend.model.Role;

public interface  RoleRepo extends JpaRepository<Role,Long>{
Optional<Role> findByName(String name);
}
