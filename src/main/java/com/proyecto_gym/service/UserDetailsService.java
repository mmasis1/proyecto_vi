package com.proyecto_gym.service;

import com.proyecto_gym.domain.Rol;
import com.proyecto_gym.domain.User;
import com.proyecto_gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNombre(username);



        //user validation
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // get the roles of the tables and add them to the roles list
        var rol= new ArrayList<GrantedAuthority>();
            rol.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().getNombreRol()));

        //return the user pass and roles
        return new org.springframework.security.core.userdetails.User(user.getNombre(), user.getContrasena(), rol);




    }
}
