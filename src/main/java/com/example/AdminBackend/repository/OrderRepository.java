package com.example.AdminBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AdminBackend.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
