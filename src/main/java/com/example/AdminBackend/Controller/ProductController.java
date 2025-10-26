package com.example.AdminBackend.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminBackend.model.Product;
import com.example.AdminBackend.repository.ProductRepository;
import com.example.AdminBackend.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController 
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")

//@PreAuthorize("hasAnyRole('ADMIN','PRODUCT_MANAGER','ECOMMERCE_MANAGER')")
public class ProductController {
     
	@Autowired
	ProductRepository productRepo;
	 @Autowired
	    private ProductService productService;

	  
	    @PostMapping
	    public ResponseEntity<?> createProduct(@RequestBody Product product) {
	        try {
	            Product saved = productService.createProduct(product);
	            return ResponseEntity.status(201).body(saved);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(
	                java.util.Map.of("message", "Invalid product data")
	            );
	        }
	    }
 
	    @GetMapping("/filter")
	    public ResponseEntity<List<Product>> filterProducts(
	            @RequestParam(required = false) String category,
	            @RequestParam(required = false, defaultValue="1") Double minPrice,
	            @RequestParam(required = false, defaultValue="5000") Double maxPrice) {
	    	
	    	System.out.println("Category: " + category);
	        System.out.println("Min Price: " + minPrice);
	        System.out.println("Max Price: " + maxPrice);
	        List<Product> products = productService.filterProducts(category, minPrice, maxPrice);
	        return ResponseEntity.ok(products);
	    }
	           
//	    @DeleteMapping
//	    public ResponseEntity<?> deleteProduct(long id)
//	    {
//	    	if( productService.deleteProduct(id))
//	    	{
//	    		return ResponseEntity.status(200).body("Product deleted successfully!!!");
//	    	}
//	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
	        if (productService.deleteProduct(id)) {
	            return ResponseEntity.ok("Product deleted successfully!!!");
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody Product p)
	    {
	    	if( productService.updateProductfun(id,p))
	    	{
	    		return ResponseEntity.status(200).body("Product Updated successfully!!!");
	    	}
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product update");
	    }
	    
	    @GetMapping("/{id}")
	    public Optional<Product> getByid(@PathVariable long id)
	    {
	    	return productService.getProductByid(id);     
	    }
	      
	    @GetMapping("/page")
	    public ResponseEntity<Page<Product>> getAllProducts(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy,
		          @RequestParam(defaultValue = "desc") String order) {
	    	  
	    	Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
	        Pageable pageable = PageRequest.of(page, size,sort);
	        Page<Product> productsPage = productRepo.findAll(pageable); 

	        return ResponseEntity.ok(productsPage); 
	    }
	    
	     
}
