package com.edu;

import com.edu.model.Product;
import com.edu.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
	private final ProductService productService;

	public ProductServiceApplication(ProductService productService) {
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		productService.putProduct(new Product(
				1L, 1, 1, "Cola light", "Phosphoric acid water solution (good for rust removal)", 120
		));
		productService.putProduct(new Product(
				2L, 2, 1, "Oat milk", "Healthy and testy yummy", 82
		));
		productService.putProduct(new Product(
				3L, 3, 1, "Cashew milk", "Healthy and testy yummy", 84
		));
		productService.putProduct(new Product(
				4L, 4, 2, "Dark rye bread", "Everyday meal", 50
		));
		productService.putProduct(new Product(
				5L, 4, 2, "Whole grained wheat bread", "Everyday meal", 55
		));
	}
}
