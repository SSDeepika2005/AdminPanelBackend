package com.example.AdminBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Inventory;
import com.example.AdminBackend.repository.InventoryRepo;

@Service
public class InventoryService {
@Autowired
	InventoryRepo inventoryRepo;
	//get all inventory data by admin
	
	public List<Inventory> getInventory() {
	    return inventoryRepo.findAll();
	}

	//update stock in inventory
	public ResponseEntity<?> updateInventory(Long id, int maxStock)
	{
		Optional<Inventory> inventorydata=inventoryRepo.findById(id);
		if(!inventorydata.isPresent())
		{
			throw new RuntimeException("Id not available");
			
		}
		Inventory data=inventorydata.get();
		
	   data.setMaxStock(maxStock);   
	   inventoryRepo.save(data);
	   return ResponseEntity.status(200).body(data);
		
	}
	
	//delete
	public ResponseEntity<?> deleteInventory(Long id)
	{
		Optional<Inventory> inventorydata=inventoryRepo.findById(id);
		if(!inventorydata.isPresent())
		{
			throw new RuntimeException("Id not available");
			
		}
		inventoryRepo.deleteById(id);
		 return ResponseEntity.status(200).body("Deleted Successfully");
	}
	
	public ResponseEntity<?>livechange(Long id, int qty)
	{
		Optional<Inventory> inventorydata=inventoryRepo.findById(id);
		if(!inventorydata.isPresent())
		{
			throw new RuntimeException("Id not available");
			
		}
		Inventory data=inventorydata.get();
		if(data.getCurrentStock()<qty)
		{
			throw new RuntimeException("Stock low quantity");
		}
		data.setCurrentStock(data.getCurrentStock()-qty);
		Inventory updatedData = inventoryRepo.save(data);
		 return ResponseEntity.status(200).body(updatedData); 
	}
	
	//create a new inventor
	public ResponseEntity<?> createInventory(Inventory inventory) {
	    Inventory saved = inventoryRepo.save(inventory);
	    return ResponseEntity.status(201).body(saved);
	}

}
