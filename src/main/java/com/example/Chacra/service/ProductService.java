package com.example.Chacra.service;

import com.example.Chacra.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = List.of(
            new Product(1L, "Huevo Rojo de Granja", new BigDecimal("16.00"), "/images/huevos.jpeg"),
            new Product(2L, "Pollo Entero Fresco", new BigDecimal("34.00"), "/images/pollos.jpg"),
            new Product(3L, "Alimento para Ponedoras (20kg)", new BigDecimal("95.00"), "/images/vitaminas.jpg"),
            new Product(4L, "Pollitos BB Parrilleros", new BigDecimal("5.00"), "/images/img2.jpg")
    );

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}