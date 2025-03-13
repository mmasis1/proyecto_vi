package com.proyecto_gym.service;

import com.proyecto_gym.domain.FormularioContacto;
import com.proyecto_gym.repository.FormularioContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioContactoService {

    @Autowired
    private FormularioContactoRepository formularioContactoRepository;

    // Obtener todos los formularios de contacto
    public List<FormularioContacto> getFormularios() {
        return formularioContactoRepository.findAll();
    }

    // Obtener un formulario de contacto por ID
    public FormularioContacto getFormularioPorId(int id) {
        return formularioContactoRepository.findById(id).orElse(null);
    }

    // Guardar formulario de contacto
    public void save(FormularioContacto formularioContacto) {
        formularioContactoRepository.save(formularioContacto);
    }

    // Eliminar formulario de contacto
    public void delete(int id) {
        formularioContactoRepository.deleteById(id);
}
}