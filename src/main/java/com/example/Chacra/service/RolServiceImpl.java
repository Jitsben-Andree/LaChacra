package com.example.Chacra.service;

import com.example.Chacra.entity.Rol;
import com.example.Chacra.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    @Override
    public Rol findByNombre(String nombre) {
        return rolRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }
}