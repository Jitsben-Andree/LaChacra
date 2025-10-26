package com.example.Chacra.service;

import com.example.Chacra.entity.Cliente;
import com.example.Chacra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return clienteRepository.existsByDni(dni);
    }
}