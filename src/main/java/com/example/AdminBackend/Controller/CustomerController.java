package com.example.AdminBackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.AdminBackend.repository.CustomerRepo;

@RestController
@RequestMapping("/api")
public class CustomerController {

@Autowired
    CustomerRepo crepo;

    @GetMapping("/getcustomer")
    public List<com.example.AdminBackend.model.Customer> getAllCustomers() {
        return crepo.findAll();
    }

     @GetMapping("/{id}/getcustomerId")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<com.example.AdminBackend.model.Customer> customer = crepo.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<?> addCustomer(@RequestBody com.example.AdminBackend.model.Customer cus) {
        com.example.AdminBackend.model.Customer saved = crepo.save(cus);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

     @PutMapping("/{id}/editcustomer")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody com.example.AdminBackend.model.Customer cus) {
        Optional<com.example.AdminBackend.model.Customer> opt = crepo.findById(id);
        if (opt.isPresent()) {
            com.example.AdminBackend.model.Customer existing = opt.get();
            existing.setFirstName(cus.getFirstName());
            existing.setLastName(cus.getLastName());
            existing.setEmail(cus.getEmail());
            existing.setPhone(cus.getPhone());
            existing.setAddress(cus.getAddress());

            com.example.AdminBackend.model.Customer updated = crepo.save(existing);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

}
