package com.sapient.saurav.productapiannotation;

import com.sapient.saurav.productapiannotation.model.Product;
import com.sapient.saurav.productapiannotation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProductApiAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiAnnotationApplication.class, args);
	}
	@Bean
	CommandLineRunner init(ProductRepository productRepository) {
		return args -> {
			Flux<Product> products = Flux.just(
					new Product(null, "Big Latte", 2.99),
					new Product(null, "Big Latte", 2.99),
					new Product(null, "Small Latte", 1.99),
					new Product(null, "Super Big Latte", 4.99),
					new Product(null, "Big Decaf", 2.99),
					new Product(null, "Small Latte", 1.99),
					new Product(null, "Super Big Latte", 4.99),
					new Product(null, "Family Big Latte", 10.99),
					new Product(null, "Big Chai", 2.99),
					new Product(null, "Big Chai", 2.99),
					new Product(null, "Small Chai", 1.99),
					new Product(null, "Super Big Chai", 4.99),
					new Product(null, "Big Ice Coffee", 2.99),
					new Product(null, "Big Ice Coffee", 2.99),
					new Product(null, "Small Ice Coffee", 1.99),
					new Product(null, "Super Big Ice Coffee", 4.99),
					new Product(null, "Big Tropical Shake", 2.99),
					new Product(null, "Big Tropical Shake", 2.99),
					new Product(null, "Small Tropical Shake", 1.99),
					new Product(null, "Super Big Tropical Shake", 4.99),
					new Product(null, "Big Lemonade", 2.99),
					new Product(null, "Big Lemonade", 2.99),
					new Product(null, "Small Lemonade", 1.99),
					new Product(null, "Super Big Lemonade", 4.99)

			).flatMap( productRepository::save);

			products.thenMany(productRepository.findAll())
			.subscribe(System.out::println);
		};
	}
}
