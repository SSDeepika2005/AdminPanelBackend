package com.example.AdminBackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.dto.OrderCreateRequest;
import com.example.AdminBackend.dto.OrderStatus;
import com.example.AdminBackend.model.Order;
import com.example.AdminBackend.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
//@RequestMapping("/api/orders")

@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

   
    @PostMapping("/api/createorder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreateRequest dto) {
        Order order = orderService.createOrder(dto); 
        return ResponseEntity.status(201).body(order);
    }

    @PatchMapping("/{id}/status")

public ResponseEntity<Order> updateOrderStatus(
        @PathVariable Long id,
        @RequestBody OrderStatus request) {

    Order updatedOrder = orderService.updateStatus(id, request.getStatus());
    return ResponseEntity.ok(updatedOrder);
}   
    @GetMapping("/{num}/{size}/getAll")   
    public org.springframework.data.domain.Page<Order> getAllProducts(@PathVariable int num, @PathVariable int size) 
    {
    	return orderService.fetchAllOrders(num,size);  
    }   
    
    @GetMapping("/{id}/getOrdersbyId")
    public Optional<Order> getOrderwithId(@PathVariable long id)
    {
    	return orderService.fetchById(id);
    	
    }
    
    @GetMapping("/fetchOrders")
    public ResponseEntity<List<Order>> getOrders()
    {
    	return orderService.getAllorders();   
    }
     

}
