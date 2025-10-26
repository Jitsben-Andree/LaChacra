package com.example.Chacra.service;

import com.example.Chacra.entity.Pago;
import com.example.Chacra.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    public void guardarPago(Pago pago) {
        pagoRepository.save(pago);
    }
}