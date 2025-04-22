package com.proyecto_gym.repository;

import com.proyecto_gym.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
