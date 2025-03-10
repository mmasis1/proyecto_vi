package com.proyecto_gym.service;

import com.proyecto_gym.domain.PreguntaFrecuente;
import com.proyecto_gym.repository.PreguntaFrecuenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PreguntaFrecuenteService {
    @Autowired
    private PreguntaFrecuenteRepository preguntaFrecuenteRepository;

    @Transactional(readOnly = true)
    public List<PreguntaFrecuente> getPreguntaFrecuentes(boolean activos) {
        {
            var lista = preguntaFrecuenteRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public PreguntaFrecuente getPreguntaFrecuente(PreguntaFrecuente preguntaFrecuente) {
        {
            preguntaFrecuente = preguntaFrecuenteRepository.findById(preguntaFrecuente.getIdPregunta()).orElse(null);
            return preguntaFrecuente;
        }
    }

    //delete
    @Transactional
    public void delete(PreguntaFrecuente preguntaFrecuente) {
        //if id is no valid, will not affect the db
        preguntaFrecuenteRepository.delete(preguntaFrecuente);
    }

    @Transactional
    public void save(PreguntaFrecuente preguntaFrecuente) {
        //if the idpreguntaFrecuente have already a value will update the row, if not will insert a new row
        preguntaFrecuenteRepository.save(preguntaFrecuente);
    }
}
