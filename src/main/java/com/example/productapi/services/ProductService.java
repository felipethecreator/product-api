package com.example.productapi.services;

import com.example.productapi.model.product.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produtos: ", error);
        }
    }
}
