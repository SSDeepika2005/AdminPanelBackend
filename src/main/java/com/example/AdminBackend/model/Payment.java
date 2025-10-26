package com.example.AdminBackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PaymentTransactions")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade=CascadeType.ALL)
	private Order ord;
	
	
	@SuppressWarnings("unused")
	private String paymentMethod;
	@SuppressWarnings("unused")
	private LocalDateTime transactionDate;
	
}
