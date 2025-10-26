package com.example.AdminBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Payment;
import com.example.AdminBackend.repository.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	PaymentRepo paymentRepo;
	public List<Payment> fetchPayment()
	{
		return paymentRepo.findAll(); 
	}
}
