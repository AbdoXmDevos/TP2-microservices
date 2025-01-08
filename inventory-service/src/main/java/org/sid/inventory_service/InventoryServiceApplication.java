package com.abdo.inventory_service;

import com.abdo.inventory_service.entities.Product;
import com.abdo.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository , RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null, "Computer", 788, 12));
			productRepository.save(new Product(null, "Printer", 88, 122));
			productRepository.save(new Product(null, "Smartphone", 88, 122));
			productRepository.findAll().forEach(product -> System.out.println(product.getName()));
		};
	}

}
