package com.example.Chacra.service;

import com.example.Chacra.entity.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    Usuario findByEmail(String email);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsByEmail(String email);
    Usuario getCurrentUser();
}