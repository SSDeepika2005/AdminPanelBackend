package com.example.AdminBackend.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderCreateRequest {

    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private List<OrderItemCreateRequest> orderItems;
}
