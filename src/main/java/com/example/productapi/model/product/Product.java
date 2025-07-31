package com.example.productapi.model.product;

import com.example.productapi.dto.RequestProduct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private BigDecimal price_in_cents;

    public Product(RequestProduct data) {
        this.name = data.name();
        this.price_in_cents = BigDecimal.valueOf(data.price_in_cents());
    }
}
