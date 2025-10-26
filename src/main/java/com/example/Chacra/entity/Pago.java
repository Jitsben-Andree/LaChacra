package com.example.Chacra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "pagos")
@Data // Lombok annotation to generate getters, setters, toString, etc.
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreTitular;

    // En una aplicación real, los datos de la tarjeta nunca deben guardarse en texto plano.
    // Esto es solo para fines de demostración.
    @Column(nullable = false)
    private String numeroTarjeta;

    @Column(nullable = false)
    private String fechaExpiracion; // Formato "MM/YY"

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private BigDecimal monto;
}