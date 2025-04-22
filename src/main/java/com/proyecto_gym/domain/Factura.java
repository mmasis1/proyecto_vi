package com.proyecto_gym.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    private int idUsuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private double total;

    private int estado;

    public Factura() {
    }

    public Factura(int idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado = 1;
    }

    
    public Long getIdFactura() {
        return idFactura;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
