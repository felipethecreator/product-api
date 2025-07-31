package com.example.productapi.controllers;

import com.example.productapi.dto.RequestProduct;
import com.example.productapi.model.product.Product;
import jakarta.validation.Valid;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity CreateProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        System.out.println(data);
        productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }
}
