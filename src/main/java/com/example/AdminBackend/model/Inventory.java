package com.example.AdminBackend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="inventory")
public class Inventory {

    
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)                                       
    private Long id;
	
	
	private Long productId;  
	
	private int maxStock;   
	private int currentStock;
	private LocalDate lastRestockedDate; 
	
	
}
