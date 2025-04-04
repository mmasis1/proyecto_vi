package com.proyecto_gym.repository;

import com.proyecto_gym.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //used in loggin
    User findByNombre(String nombre);
    //   find usuario by username and password
    User findByNombreAndContrasena(String nombre, String contrasena);
    //to check if the user exists username or correo
    User findByNombreOrCorreo(String nombre, String correo);
    boolean existsByNombreOrCorreo(String nombre, String correo);

}
