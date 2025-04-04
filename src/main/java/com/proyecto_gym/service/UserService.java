package com.proyecto_gym.service;

import com.proyecto_gym.domain.User;
import com.proyecto_gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers(boolean activos) {
        {
            var lista = userRepository.findAll();
            return lista;
        }
    }
    @Transactional(readOnly = true)
    public User getUser(User user) {
        {
            user = userRepository.findById(user.getIdUsuario()).orElse(null);
            return user;
        }
    }
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUsuarioPorUsername(String username) {
        return userRepository.findByNombre(username);
    }

    @Transactional(readOnly = true)
    public User getUsuarioPorUsernameYPassword(String username, String password) {
        return userRepository.findByNombreAndContrasena(username, password);
    }

    @Transactional(readOnly = true)
    public User getUsuarioPorUsernameOCorreo(String username, String correo) {
        return userRepository.findByNombreOrCorreo(username, correo);
    }

    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return userRepository.existsByNombreOrCorreo(username, correo);
    }
}
