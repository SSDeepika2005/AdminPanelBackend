package com.example.AdminBackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  
@Data 
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long shipmentId;

	   @Column(nullable = false)
	    private Long orderId;   // FK (we keep it simple: just store orderId)

	    private String carrier;
	    private String trackingNumber;

	    @Enumerated(EnumType.STRING)   //  Enum stored as string
	    private ShipmentStatus status;
   
	    private LocalDateTime shippedDate;
	    private LocalDateTime deliveredDate;
	    private LocalDateTime lastUpdated;
}
