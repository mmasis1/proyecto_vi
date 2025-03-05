package com.proyecto_gym.service;

import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        {
            var lista = productoRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        {
            producto = productoRepository.findById(producto.getIdProducto()).orElse(null);
            return producto;
        }
    }

    //delete
    @Transactional
    public void delete(Producto producto) {
        //if id is no valid, will not affect the db
        productoRepository.delete(producto);
    }

    @Transactional
    public void save(Producto producto) {
        //if the idproducto have already a value will update the row, if not will insert a new row
        productoRepository.save(producto);
    }
}
