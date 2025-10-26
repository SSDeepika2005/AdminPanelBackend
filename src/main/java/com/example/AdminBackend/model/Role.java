package com.example.AdminBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="roles")
@Entity 
@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class Role {
@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	 @Column(unique = true, nullable = false)
	private String name;//ADMIN, E COMMERCE MANAGER, Product Manager 
}
