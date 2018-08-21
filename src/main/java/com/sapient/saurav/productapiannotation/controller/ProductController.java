package com.sapient.saurav.productapiannotation.controller;

import com.sapient.saurav.productapiannotation.model.Product;
import com.sapient.saurav.productapiannotation.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    final private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/allProducts")
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable String id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {

        return productRepository.save(product)
                .defaultIfEmpty(new Product());
    }
    @PutMapping
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable String id,@RequestBody Product product){
        return  productRepository.findById(id)
                .flatMap(existingProduct->{
                    existingProduct.setId(product.getId());
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                })
                .map(updatedProduct-> ResponseEntity.ok(updatedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping ("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") String id){
        return  productRepository.findById(id)
                .flatMap(existingProduct-> productRepository.delete(existingProduct)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
