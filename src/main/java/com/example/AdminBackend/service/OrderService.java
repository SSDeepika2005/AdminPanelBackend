package com.example.AdminBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.AdminBackend.dto.OrderCreateRequest;
import com.example.AdminBackend.dto.OrderItemCreateRequest;
import com.example.AdminBackend.model.Order;
import com.example.AdminBackend.model.OrderItem;
import com.example.AdminBackend.model.Product;
import com.example.AdminBackend.repository.OrderRepository;
import com.example.AdminBackend.repository.ProductRepository;

@Service
public class OrderService {

     private final ProductRepository productRepository;
	    private final OrderRepository orderRepository;

	    @Autowired
	    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
	        this.productRepository = productRepository;
	        this.orderRepository = orderRepository;
	    }

	    public Order createOrder(OrderCreateRequest dto) {
	    	List<OrderItem> orderItems = new ArrayList<>();
 
	        double total = 0.0;
	        Order order = new Order();

	        for (OrderItemCreateRequest itemDto : dto.getOrderItems()) {
	            Product product = productRepository.findById(itemDto.getProductId())
	                    .orElseThrow(() -> new RuntimeException("Product not found"));

	            if (product.getStockQuantity() < itemDto.getQuantity()) {
	                throw new IllegalArgumentException("Insufficient stock");
	            }

	           product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity());
	productRepository.save(product);

	OrderItem orderItem = new OrderItem();
	orderItem.setProduct(product);
	orderItem.setQuantity(itemDto.getQuantity());
	orderItem.setPriceAtPurchase(product.getPrice());
	orderItem.setOrder(order);

	orderItems.add(orderItem);
	total += product.getPrice() * itemDto.getQuantity();
	}

	order.setCustomerName(dto.getCustomerName());
	order.setCustomerEmail(dto.getCustomerEmail());
	order.setShippingAddress(dto.getShippingAddress());
	order.setOrderItems(orderItems);
	order.setTotalAmount(total);
	order.setStatus("PENDING");
	order.setOrderDate(java.time.LocalDateTime.now());

	return orderRepository.save(order);
	}
    
	public Order updateStatus(Long id, String status) {
	Order order = orderRepository.findById(id)
	.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

	if (!List.of("PENDING", "CREATED", "SHIPPED", "DELIVERED", "CANCELLED").contains(status)) {
	throw new IllegalArgumentException("Invalid status");
	}

	order.setStatus(status);
	return orderRepository.save(order);
	
	}
//	}if (updated == null) {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
//    }
//
//    return ResponseEntity.ok(updated);
//	   
	
	public Page<Order> fetchAllOrders(int num, int size)
	{  
		Pageable pages=PageRequest.of(num, size);
		return orderRepository.findAll( pages);
	}
	
	public Optional<Order>fetchById(Long id)
	{
		return orderRepository.findById(id); 
	}
	
	public ResponseEntity<List<Order>> getAllorders()
	{  
		return ResponseEntity.status(200).body(orderRepository.findAll());  
	}
}
