package com.example.AdminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.model.Shipment;
import com.example.AdminBackend.model.ShipmentStatus;

@RestController
public class ShipmentController {

	
	@Autowired 
	com.example.AdminBackend.service.ShipmentService shipmentService;   
	 @PostMapping("/api/shipmentspost")
	    public Shipment createShipment(@RequestBody Shipment shipment) {
	        return shipmentService.createShipment(shipment);
	    }

	    // Update shipment status
	    @PutMapping("/{id}/status")
	    public Shipment updateShipmentStatus(@PathVariable Long id, @RequestParam ShipmentStatus status) {
	        return shipmentService.updateShipmentStatus(id, status);
	    }

	    // Get all shipments
	    @GetMapping("/api/shipmentget")
	    public List<Shipment> getAllShipments() {
	        return shipmentService.getAllShipments();
	    }
	    
	    @GetMapping("/status/{status}")
	    public List<Shipment> getShipmentsByStatus(@PathVariable ShipmentStatus status) {
	        return shipmentService.getShipmentsByStatus(status);
	    }
}
