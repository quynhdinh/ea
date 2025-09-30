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
		productService.deleteAllProducts();
		// Add some products
		Long product2 = productService.createProduct("iPhone 17", 2000);
		System.out.println("Product created id: " + product2);
		productService.retrieveProduct(product2).ifPresent(product -> {
			System.out.println("ID: " + product.getId() + 
							   ", Name: " + product.getName() + 
							   ", Price: $" + product.getPrice() + 
							   ", Version: " + product.getVersion());
		});
		productService.updateProductPrice(product2, 3000);
		productService.retrieveProduct(product2).ifPresent(product -> {
			System.out.println("ID: " + product.getId() + 
							   ", Name: " + product.getName() + 
							   ", Price: $" + product.getPrice() + 
							   ", Version: " + product.getVersion());
		});
	}

}