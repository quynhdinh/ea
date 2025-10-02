package edu.miu.cs544.reactive_product_api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}

interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}

@Service
class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(String id, Product product) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                });
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
@RestController
class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/api/products")
	public Flux<Product> getAllProducts() {
		return productService.findAllProducts();
	}
	@GetMapping("/api/products/{id}")
	public Mono<ResponseEntity<Product>> getProductById(@PathVariable String id) {
        return productService.findProductById(id)
            .map(product -> ResponseEntity.ok(product))
            .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@PostMapping("/api/products")
	public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        return productService.saveProduct(product)
            .map(savedProduct -> {
                URI location = URI.create("/api/products/" + savedProduct.getId());
                return ResponseEntity.created(location).body(savedProduct);
            });
	}
	@PutMapping("/api/products/{id}")
	public Mono<ResponseEntity<Product>> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
            .map(updatedProduct -> ResponseEntity.ok(updatedProduct))
            .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/api/products/{id}")
	public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id)
            .then(Mono.just(ResponseEntity.noContent().<Void>build()))
            .defaultIfEmpty(ResponseEntity.notFound().build());
	}
}