package com.cs544.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.*;
import lombok.*;
import jakarta.persistence.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;

@Entity
@Getter
@Setter
@NoArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @Version
    private Long version;
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}

@SpringBootApplication
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
@Repository
interface ProductRepository extends JpaRepository<Product, Long> {}

@Service
@Transactional
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(String name, double price) {
        Product product = productRepository.save(new Product(name, price));
        return product.getId();
    }

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
        // Simulate two concurrent updates
        Thread thread1 = new Thread(() -> {
            try {
                productService.updateProductPrice(product2, 130.0);
            } catch (ObjectOptimisticLockingFailureException e) {
                System.out.println("Optimistic locking failure in thread 1!");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                productService.updateProductPrice(product2, 140.0);
            } catch (ObjectOptimisticLockingFailureException e) {
                System.out.println("Optimistic locking failure in thread 2!");
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

}