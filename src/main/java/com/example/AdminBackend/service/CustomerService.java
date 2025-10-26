package com.example.AdminBackend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Customer;
import com.example.AdminBackend.repository.CustomerRepo;

@Service
public class CustomerService {
@Autowired 
    private CustomerRepo crepo;

    // Get customer by ID
    public ResponseEntity<?> getCustomerById(long id) {
        Optional<Customer> ans = crepo.findById(id);
        if (ans.isPresent()) {
            return ResponseEntity.ok(ans.get()); // return actual Customer
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    // Get all customers
    public List<Customer> getCustomer() {
        return crepo.findAll();
    }

    // Add new customer
    public ResponseEntity<?> addCustomers(Customer cus) {

        // Validate required fields
        if (cus.getFirstName() == null || cus.getFirstName().trim().isEmpty() ||
            cus.getLastName() == null || cus.getLastName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("First Name and Last Name are required");
        }

        // Set date if not provided
        if (cus.getDate() == null) {
            cus.setDate(LocalDate.now());
        }

        // Save customer
        Customer saved = crepo.save(cus);

        // Return the saved customer with ID and date
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
