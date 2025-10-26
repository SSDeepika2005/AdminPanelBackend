package com.example.AdminBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AdminBackend.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findByCategory(String category);

	boolean findByName(String name);
}
