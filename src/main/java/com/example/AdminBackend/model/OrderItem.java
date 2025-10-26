package com.example.AdminBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    @JsonIgnore   // prevent infinite recursion when serializing Order → OrderItem → Order → ...
	    private Order order; 

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    private int quantity;

	    private double priceAtPurchase;
}
  