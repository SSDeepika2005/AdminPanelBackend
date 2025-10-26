package com.example.AdminBackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.authentication.JwtUtil;
import com.example.AdminBackend.repository.CustomerRepo;
import com.example.AdminBackend.repository.OrderRepository;
import com.example.AdminBackend.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class DashboardController {

//    @GetMapping("/counts")
//    public String getDashboard(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            JwtUtil jwtUtil = new JwtUtil();  
//            boolean valid = JwtUtil.validateToken(token);
//            if (valid) {
//                return "Welcome to your Dashboard ";
//            }
//        }
//        return "Unauthorized ";
//    }
//    
//    @Autowired
//      ProductRepository productRepo;
//    @Autowired
//	  CustomerRepo customerRepo;
//    @GetMapping("/customers/count")
//    public ResponseEntity<Map<String, Long>> getCustomerCount(HttpServletRequest request) {
//        if (!isTokenValid(request)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//     
//		long count = customerRepo.count();
//        return ResponseEntity.ok(Map.of("count", count));
//    }
//    
//    @GetMapping("/products/count")
//    public ResponseEntity<Map<String, Long>> getProductCount(HttpServletRequest request) {
//        if (!isTokenValid(request)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        long count = productRepo.count();
//        return ResponseEntity.ok(Map.of("count", count));
//    }
//    private static boolean isTokenValid(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
//        String token = authHeader.substring(7);
//        return JwtUtil.validateToken(token);
//    }
//    
	

	
@Autowired OrderRepository orderRepo;
	    private final ProductRepository productRepo;
	    private final CustomerRepo customerRepo;
	    private final JwtUtil jwtUtil;

	    public DashboardController(ProductRepository productRepo,
	                               CustomerRepo customerRepo,
	                               JwtUtil jwtUtil) {
	        this.productRepo = productRepo;
	        this.customerRepo = customerRepo;
	        this.jwtUtil = jwtUtil;
	    }  

	    @GetMapping("/customers/counts")
	    public ResponseEntity<?> getcustomerCounts(HttpServletRequest request) {
	        if (!isTokenValid(request)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
	        }
	        
	        
	       
	        long customerCount = customerRepo.count();
	        return ResponseEntity.ok(Map.of(
	              
	            "count", customerCount
	        ));
	    }
	    
	    @GetMapping("/products/counts")
	    public ResponseEntity<?> getCounts(HttpServletRequest request) {
	        if (!isTokenValid(request)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
	        }
	        long productCount = productRepo.count();
	 
	      
	        return ResponseEntity.ok(Map.of("count", productCount));
	    }
	    
	    @GetMapping("/orders/counts")   
	    public ResponseEntity<?> getCountsOrders(HttpServletRequest request) {
	        if (!isTokenValid(request)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
	        }
	        long OrderCount = orderRepo.count();
	 
	      
	        return ResponseEntity.ok(Map.of("count", OrderCount));
	    }

	    private boolean isTokenValid(HttpServletRequest request) {
	        String authHeader = request.getHeader("Authorization");
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
	        String token = authHeader.substring(7);
	        System.out.println(jwtUtil.validateToken(token));
	        
	        return jwtUtil.validateToken(token); 
	    }
	    
	    @GetMapping("/revenue/yearly")
	    public ResponseEntity<List<Map<String, Object>>> getYearlyRevenue() {
	        List<Map<String, Object>> revenueData = new ArrayList<>();

	        // Example: hard-coded values, replace with DB query later
	        revenueData.add(Map.of("year", 2019, "revenue", 10000));
	        revenueData.add(Map.of("year", 2020, "revenue", 90000));
	        revenueData.add(Map.of("year", 2021, "revenue", 150000));
	        revenueData.add(Map.of("year", 2022, "revenue", 130000));
	        revenueData.add(Map.of("year", 2023, "revenue", 160000));
	        revenueData.add(Map.of("year", 2024, "revenue", 145000));
	        revenueData.add(Map.of("year", 2025, "revenue", 200000));

	        return ResponseEntity.ok(revenueData);
	    }

}
