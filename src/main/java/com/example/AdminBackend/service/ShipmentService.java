package com.example.AdminBackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Shipment;
import com.example.AdminBackend.model.ShipmentStatus;
import com.example.AdminBackend.repository.ShipmentRepo;

@Service
public class ShipmentService {

	@Autowired 
	private ShipmentRepo shipmentRepo;
	 // Create shipment
    public Shipment createShipment(Shipment shipment) {
        shipment.setStatus(ShipmentStatus.PENDING);
        shipment.setLastUpdated(LocalDateTime.now());
        return shipmentRepo.save(shipment);
    }
    
    public Shipment updateShipmentStatus(Long shipmentId, ShipmentStatus newStatus) {
    	
    	Optional<Shipment> shipmentdatas=shipmentRepo.findById(shipmentId);
    	
    	if(shipmentdatas.isEmpty())
    	{
    		throw new RuntimeException("Order is Empty");
    	}
    	Shipment shipment=shipmentdatas.get();
    	
    	 shipment.setStatus(newStatus);
         shipment.setLastUpdated(LocalDateTime.now());

         if (newStatus == ShipmentStatus.SHIPPED) {
             shipment.setShippedDate(LocalDateTime.now());
         }
         if (newStatus == ShipmentStatus.DELIVERED) {
             shipment.setDeliveredDate(LocalDateTime.now());
         }

         return shipmentRepo.save(shipment);
    }
    
    // Get all shipments
    public List<Shipment> getAllShipments() {
        return shipmentRepo.findAll();
    }
       
    // Get shipments by status
    public List<Shipment> getShipmentsByStatus(ShipmentStatus status) {
        return shipmentRepo.findByStatus(status);
    }
}

