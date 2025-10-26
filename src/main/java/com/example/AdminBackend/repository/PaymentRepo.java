package com.example.AdminBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AdminBackend.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long>{

}
