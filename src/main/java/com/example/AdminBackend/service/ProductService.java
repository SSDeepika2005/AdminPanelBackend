package com.example.AdminBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AdminBackend.model.Product;
import com.example.AdminBackend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()
                || product.getPrice() == null || product.getPrice() < 0
                || product.getCategory() == null || product.getCategory().isBlank()) {
            throw new IllegalArgumentException("Invalid product data");
        }
        return productRepository.save(product);
    }

    // Filter products
    public List<Product> filterProducts(String category, Double minPrice, Double maxPrice) {
        List<Product> all = productRepository.findAll();
        List<Product> ans = new ArrayList<>();

        for (Product p : all) {
            boolean match = true;

            if (category != null && !category.isBlank()) {
                if (p.getCategory() == null || !p.getCategory().equalsIgnoreCase(category)) {
                    match = false;
                }
            }

            if (minPrice != null && p.getPrice() < minPrice) match = false;
            if (maxPrice != null && p.getPrice() > maxPrice) match = false;

            if (match) ans.add(p);
        }
        return ans;
    }

    // Get all products
    public List<Product> allproducts() {
        return productRepository.findAll();
    }

    // Delete a product
    public boolean deleteProduct(long id) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Update a product
    public boolean updateProductfun(long id, Product pdetail) {
        Optional<Product> plist = productRepository.findById(id);
        if (plist.isPresent()) {
            Product each_product = plist.get();
            each_product.setName(pdetail.getName());
            each_product.setPrice(pdetail.getPrice());
            each_product.setImageUrl(pdetail.getImageUrl());
            each_product.setStockQuantity(pdetail.getStockQuantity());
            each_product.setCategory(pdetail.getCategory());

            productRepository.save(each_product);
            return true;
        }
        return false;
    }

    // Get product by ID
    public Optional<Product> getProductByid(long id) {
        return productRepository.findById(id);
    }
}
