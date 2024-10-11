package com.ecommerce.commerce.services;

import com.ecommerce.commerce.dto.ProductRequest;
import com.ecommerce.commerce.models.Product;
import com.ecommerce.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(ProductRequest request) {
        Product prod = new Product();

        prod.setName(request.getName());
        prod.setDescription(request.getDescription());
        prod.setPrice(request.getPrice());
        prod.setStock(request.getStock());

        return productRepository.save(prod);
    }
}
