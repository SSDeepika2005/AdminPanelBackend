package com.example.AdminBackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
@Builder
public class Customer {

@Id       
	 @GeneratedValue(strategy = GenerationType.IDENTITY)                                       
	private Long customer_id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private LocalDate date;
	@OneToMany(mappedBy="customer",  cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	@JsonIgnore 
	List<Order> order_cust=new ArrayList<>();
	// Automatically set date when inserting
    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}
