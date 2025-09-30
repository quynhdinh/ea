package com.cs544.transaction.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs544.transaction.entity.Product;
import com.cs544.transaction.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(String name, double price) {
        Product product = productRepository.save(new Product(name, price));
        return product.getId();
    }
    // delete all products
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public Optional<Product> retrieveProduct(Long id) {
        return productRepository.findById(id);
    }

    public void updateProductPrice(Long productId, double newPrice) {
        productRepository.findById(productId).ifPresent(product -> {
            product.setPrice(newPrice);
            productRepository.save(product);
        });
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
