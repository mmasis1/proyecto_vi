package com.proyecto_gym.service;

import com.proyecto_gym.domain.Categoria;
import com.proyecto_gym.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        {
            var lista = categoriaRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        {
            categoria = categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
            return categoria;
        }
    }

    //delete
    @Transactional
    public void delete(Categoria categoria) {
        //if id is no valid, will not affect the db
        categoriaRepository.delete(categoria);
    }

    @Transactional
    public void save(Categoria categoria) {
        //if the idcategoria have already a value will update the row, if not will insert a new row
        categoriaRepository.save(categoria);
    }
}
