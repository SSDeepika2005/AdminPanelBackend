package com.example.AdminBackend.dto;

import lombok.Data;

@Data 
public class OrderItemCreateRequest {

	


    private Long productId;
    private int quantity;
}
