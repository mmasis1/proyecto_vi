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
    private PreguntaFrecuenteRepository preguntafrecuenteRepository;

    @Transactional(readOnly = true)
    public List<PreguntaFrecuente> getPreguntaFrecuentes(boolean activos) {
        {
            var lista = preguntafrecuenteRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public PreguntaFrecuente getPreguntaFrecuente(PreguntaFrecuente preguntafrecuente) {
        {
            preguntafrecuente = preguntafrecuenteRepository.findById(preguntafrecuente.getIdPreguntaFrecuente()).orElse(null);
            return preguntafrecuente;
        }
    }

    //delete
    @Transactional
    public void delete(PreguntaFrecuente preguntafrecuente) {
        //if id is no valid, will not affect the db
        preguntafrecuenteRepository.delete(preguntafrecuente);
    }

    @Transactional
    public void save(PreguntaFrecuente preguntafrecuente) {
        //if the idpreguntafrecuente have already a value will update the row, if not will insert a new row
        preguntafrecuenteRepository.save(preguntafrecuente);
    }
}
