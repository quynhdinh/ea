package com.cs544.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs544.transaction.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}