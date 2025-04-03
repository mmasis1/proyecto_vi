package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "rol") //table name from the db
public class Rol {
    private static final long serialVersionUID = 1L; //getter autoincremental id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    private String nombreRol;
    @OneToMany
    @JoinColumn(name = "id_rol")
    private List<User> usuarios;

    public List<User> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }
    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}//end class
