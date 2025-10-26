package com.example.Chacra.service;

import com.example.Chacra.entity.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente save(Cliente cliente);
    Cliente findById(Long id);
    List<Cliente> findAll();
    void deleteById(Long id);
    boolean existsByDni(String dni);
}