package com.proyecto_gym.service;

import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.domain.Rol;
import com.proyecto_gym.repository.ProductoRepository;
import com.proyecto_gym.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<Rol> getRoles(boolean activos) {
        {
            var lista = rolRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public Rol getRol(Rol rol) {
        {
            rol = rolRepository.findById(rol.getIdRol()).orElse(null);
            return rol;
        }
    }

    //delete
    @Transactional
    public void delete(Rol rol) {
        //if id is no valid, will not affect the db
        rolRepository.delete(rol);
    }

    @Transactional
    public void save(Rol rol) {
        //if the idproducto have already a value will update the row, if not will insert a new row
        rolRepository.save(rol);
    }
}
