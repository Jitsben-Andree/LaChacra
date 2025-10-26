package com.example.Chacra.repository;

import com.example.Chacra.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long>{
        }