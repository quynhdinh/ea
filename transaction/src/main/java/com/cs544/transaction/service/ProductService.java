package com.cs544.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs544.transaction.entity.Product;
import com.cs544.transaction.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String name, double price) {
        productRepository.save(new Product(name, price));
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
