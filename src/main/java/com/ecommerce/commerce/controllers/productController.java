package com.ecommerce.commerce.controllers;

import com.ecommerce.commerce.dto.ProductRequest;
import com.ecommerce.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class productController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productService.create(request);

        return ResponseEntity.ok("Product created successfully.");
    }

}
