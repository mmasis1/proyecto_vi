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

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public User getUser(User user) {
        {
            user = userRepository.findById(user.getIdUsuario()).orElse(null);
            return user;
        }
    }

    //delete
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
