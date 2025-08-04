package com.example.productapi.services;

import com.example.productapi.dto.RequestProduct;
import com.example.productapi.model.product.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public Product getProductById(String id) {
        try {
            return productRepository.findById(id).
                    orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado"));
        } catch (Exception error) {
            throw new RuntimeException("Erro ao buscar produtos: ", error);
        }
    }

    public Product addProduct(RequestProduct data) {
        try {
            Product newProduct = new Product(data);
            return productRepository.save(newProduct);
        }  catch (Exception error) {
            throw new RuntimeException("Erro ao adicionar produto: ", error);
        }
    }

    public Product updateProduct(String id, RequestProduct data) {
        try {
            return productRepository.findById(id)
                    .map(existingProduct -> {
                        existingProduct.setName(data.name());
                        existingProduct.setPrice_in_cents(BigDecimal.valueOf(data.price_in_cents()));
                        Product updatedProduct = productRepository.save(existingProduct);
                        return ResponseEntity.ok(updatedProduct);
                    })
                    .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado")).getBody();
        } catch (RuntimeException error) {
            throw new RuntimeException("Erro ao atualizar produto: ", error);
        }
    }

    public void deleteProduct(String id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new RuntimeException("Produto com ID " + id + " não encontrado");
            }
            productRepository.deleteById(id);
        }   catch (Exception error) {
            throw new RuntimeException("Erro ao deletar produto: ", error);
        }
    }
}
