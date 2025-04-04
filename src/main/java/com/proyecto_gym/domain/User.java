package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "usuario") //table name from the db
public class User implements Serializable {
    private static final long serialVersionUID = 1L; //getter autoincremental id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private Date fechaRegistro;
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @PrePersist
    public void prePersist() {
        if (this.rol == null) {
            this.rol = new Rol();
            this.rol.setIdRol(2); // Set default role ID here
        }
    }
}//end class
