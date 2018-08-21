package com.sapient.saurav.productapiannotation.repository;


import com.sapient.saurav.productapiannotation.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

    Flux<Product> findByName(String name);
}
