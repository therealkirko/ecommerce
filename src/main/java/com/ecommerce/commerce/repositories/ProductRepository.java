package com.ecommerce.commerce.repositories;

import com.ecommerce.commerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
