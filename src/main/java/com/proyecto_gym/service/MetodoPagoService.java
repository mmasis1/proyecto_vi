package com.proyecto_gym.service;

import com.proyecto_gym.domain.MetodoPago;
import com.proyecto_gym.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MetodoPagoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Transactional(readOnly = true)
    public List<MetodoPago> getMetodoPagos(boolean activos) {
        {
            var lista = metodoPagoRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public MetodoPago getMetodoPago(MetodoPago metodoPago) {
        {
            metodoPago = metodoPagoRepository.findById(metodoPago.getIdMetodoPago()).orElse(null);
            return metodoPago;
        }
    }

    //delete
    @Transactional
    public void delete(MetodoPago metodoPago) {
        //if id is no valid, will not affect the db
        metodoPagoRepository.delete(metodoPago);
    }

    @Transactional
    public void save(MetodoPago metodoPago) {
        //if the idmetodoPago have already a value will update the row, if not will insert a new row
        metodoPagoRepository.save(metodoPago);
    }
}
