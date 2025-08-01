package com.example.productapi.controllers;

import com.example.productapi.ProductApiApplication;
import com.example.productapi.dto.RequestProduct;
import com.example.productapi.model.product.Product;
import jakarta.validation.Valid;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping
    public ResponseEntity GetAllProducts() {
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity GetProductById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity CreateProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        System.out.println(data);
        productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(data.name());
                    existingProduct.setPrice_in_cents(BigDecimal.valueOf(data.price_in_cents()));
                    Product updatedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());

    }
}
