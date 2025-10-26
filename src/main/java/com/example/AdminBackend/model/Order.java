package com.example.AdminBackend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String customerName;

	    private String customerEmail;

	    private String shippingAddress;

	    private String status;

	    private Double totalAmount;

	    @Column(name="order_date")
	    private LocalDateTime orderDate;  

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Builder.Default
	    private List<OrderItem> orderItems = new ArrayList<>();

	    // helper methods
	    public void addOrderItem(OrderItem item) {
	        orderItems.add(item);
	        item.setOrder(this);
	    }

	    public void removeOrderItem(OrderItem item) {
	        orderItems.remove(item);
	        item.setOrder(null);
	    }
	    
	    @ManyToOne 
	    @JoinColumn(name="customer_id")
	    private Customer customer;
	    
	    @OneToOne(mappedBy="ord")
	    private Payment pay;
}
