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
        return productoRepository.findAll();
    }

    // Método original para obtener producto desde objeto
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }

    // ✅ Método nuevo para obtener producto desde ID (necesario para el carrito)
    @Transactional(readOnly = true)
    public Producto getProductoPorId(int idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    @Transactional
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }

    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
}
