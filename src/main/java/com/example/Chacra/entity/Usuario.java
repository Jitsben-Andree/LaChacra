package com.example.Chacra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    private Boolean estado = true;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
