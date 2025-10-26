package com.example.AdminBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AdminBackend.model.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long>{

}
