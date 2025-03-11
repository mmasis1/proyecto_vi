package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metodo_pago") // Nombre de la tabla en la base de datos
public class MetodoPago {
    private static final long serialVersionUID = 1L; // Serialización

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetodoPago;
    private String nombreMetodo;

    // Getter y Setter para idMetodoPago
    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    // Getter y Setter para nombreMetodo
    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }
}
