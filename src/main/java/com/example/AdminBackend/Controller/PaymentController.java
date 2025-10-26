package com.example.AdminBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired 
	PaymentService paymentService;
	@GetMapping("/api/paymentget")
	public void getPaymentDetails()
	{
		paymentService.fetchPayment();
	}
}
