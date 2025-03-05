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
    
    @Transactional(readOnly = true)
    public PreguntaFrecuente getPreguntaFrecuente(PreguntaFrecuente preguntafrecuente) {
        {
            preguntafrecuente = preguntaFrecuenteRepository.findById(preguntafrecuente.getIdPregunta()).orElse(null);
            return preguntafrecuente;
        }
    }
    
    @Transactional
    public void delete(PreguntaFrecuente preguntafrecuente) {
        {
            //Elimina el registro que tiene el idPregunta pasado en el objeto preguntafrecuente
            preguntaFrecuenteRepository.delete(preguntafrecuente);
        }
    }
    
    @Transactional
    public void save(PreguntaFrecuente preguntafrecuente) {
        {
            //Si el idPregunta tiene un valor... se actualiza el registro de ese idPreguntaFrecuente
            //Si el idPregunta tiene valor... se inserta un registro con la información de idPreguntaFrecuente
            preguntaFrecuenteRepository.save(preguntafrecuente);
        }
    }
}