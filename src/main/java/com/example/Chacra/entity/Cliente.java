package com.example.Chacra.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, length = 15)
    private String dni;

    @Column(length = 150)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;
}
