package com.example.AdminBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.model.Inventory;
import com.example.AdminBackend.service.InventoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/inventory")
public class InventoyController {

	

	    @Autowired
	    private InventoryService inventoryService;

	    // Get all inventory  
	    @GetMapping
	    public List<Inventory> getInventory() {
	        return inventoryService.getInventory();
	    }


	   
	   
	    //Create new inventory
	    @PostMapping
	    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
	        return inventoryService.createInventory(inventory);
	    }

	    //Update max stock
	    @PutMapping("/{id}/update/{maxStock}")
	    public ResponseEntity<?> updateInventory(@PathVariable Long id, @PathVariable int maxStock) {
	        return inventoryService.updateInventory(id, maxStock);
	    }

	    //Reduce stock 
	    @PutMapping("/{id}/reduce/{qty}")
	    public ResponseEntity<?> livechange(@PathVariable Long id, @PathVariable int qty) {
	        return inventoryService.livechange(id, qty);
     	    }

	    //Delete inventory  
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
	    	
	    	 System.out.println("Deleting inventory with id: " + id);
	        return inventoryService.deleteInventory(id);
	    }
	

}
