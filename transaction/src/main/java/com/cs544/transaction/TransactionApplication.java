package com.cs544.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.cs544.transaction.entity.Product;
import com.cs544.transaction.service.ProductService;
@SpringBootApplication
public class TransactionApplication {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}


}
@Component
class InitSomeProducts implements CommandLineRunner {
	@Autowired
	private ProductService productService;

	@Override
	public void run(String... args) throws Exception {
		// Add some products
		productService.addProduct("Product1", 100);
		productService.addProduct("Product2", 200);
		productService.addProduct("Product3", 300);
		
		// Get and display all products
		System.out.println("=== All Products ===");
		Iterable<Product> products = productService.getAllProducts();
		products.forEach(product -> {
			System.out.println("ID: " + product.getId() + 
							   ", Name: " + product.getName() + 
							   ", Price: $" + product.getPrice() + 
							   ", Version: " + product.getVersion());
		});
		System.out.println("===================");
	}

}