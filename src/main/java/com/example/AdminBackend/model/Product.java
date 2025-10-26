package com.example.AdminBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="products")
public class Product {

     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;  

	    private String description;

	    private Double price;

	    private String category;

	    private Integer stockQuantity;

	    private String imageUrl;
	    
	   @ManyToOne      
	   @JoinColumn(name="user_id")
	   @JsonIgnore
	   private User userid;//this is not bidirectional only many product connected to 1 user
	   
}
