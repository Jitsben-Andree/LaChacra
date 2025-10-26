package com.example.Chacra.service;

import com.example.Chacra.entity.Rol;
import java.util.List;

public interface RolService {
    Rol save(Rol rol);
    Rol findById(Long id);
    Rol findByNombre(String nombre);
    List<Rol> findAll();
    void deleteById(Long id);
}