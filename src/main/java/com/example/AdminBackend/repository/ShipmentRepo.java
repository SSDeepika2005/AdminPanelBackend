package com.example.AdminBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AdminBackend.model.Shipment;
import com.example.AdminBackend.model.ShipmentStatus;

public interface ShipmentRepo extends JpaRepository<Shipment,Long>{
    List<Shipment> findByStatus(ShipmentStatus status);
}
