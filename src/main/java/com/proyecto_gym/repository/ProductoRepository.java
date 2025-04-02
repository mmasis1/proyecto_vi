package com.proyecto_gym.repository;

import com.proyecto_gym.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository 
        extends JpaRepository<Producto, Integer> {
    
      //Consulta ampliada
    public List<Producto> findByPrecioBetweenOrderByPrecio(
            double precioInf,
            double precioSup);

}
