package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "metodo_pago") // Nombre de la tabla en la base de datos
public class MetodoPago implements Serializable {
    private static final long serialVersionUID = 1L; // Serialización

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_metodo_pago")
    private int idMetodoPago;
    private String nombreMetodo;

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }
}
