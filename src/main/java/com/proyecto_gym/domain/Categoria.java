package com.proyecto_gym.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria") //table name from the db
public class Categoria {
    private static final long serialVersionUID = 1L; //getter autoincremental id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;
    private String nombreCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

}//end class
