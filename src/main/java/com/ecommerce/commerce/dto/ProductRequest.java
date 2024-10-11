package com.ecommerce.commerce.dto;

public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
