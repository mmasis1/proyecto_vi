package com.proyecto_gym.service;

import com.proyecto_gym.domain.FormularioContacto;
import com.proyecto_gym.repository.FormularioContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioContactoService {
    @Autowired
    private FormularioContactoRepository formularioContactoRepository;

    @Transactional(readOnly = true)
    public List<FormularioContacto> getFormularioContactos(boolean activos) {
        {
            var lista = formularioContactoRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public FormularioContacto getFormularioContacto(FormularioContacto formularioContacto) {
        {
            formularioContacto = formularioContactoRepository.findById(formularioContacto.getIdContacto()).orElse(null);
            return formularioContacto;
        }
    }

    //delete
    @Transactional
    public void delete(FormularioContacto formularioContacto) {
        //if id is no valid, will not affect the db
        formularioContactoRepository.delete(formularioContacto);
    }

    @Transactional
    public void save(FormularioContacto formularioContacto) {
        //if the idformularioContacto have already a value will update the row, if not will insert a new row
        formularioContactoRepository.save(formularioContacto);
    }
}
