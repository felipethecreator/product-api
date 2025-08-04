package com.example.productapi.controllers;

import com.example.productapi.ProductApiApplication;
import com.example.productapi.dto.RequestProduct;
import com.example.productapi.model.product.Product;
import com.example.productapi.services.ProductService;
import jakarta.validation.Valid;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> GetAllProducts() {
        var allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity GetProductById(@PathVariable String id) {
        try {
            var product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> CreateProduct(@RequestBody @Valid RequestProduct data) {
        var newProduct = productService.addProduct(data);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data) {
        try {
            ProductService productService = new ProductService();
            var product = productService.updateProduct(id, data);
            return ResponseEntity.ok(product);
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.notFound().build();
        }
    }
}
