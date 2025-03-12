package com.proyecto_gym.controller;

import com.proyecto_gym.domain.Categoria;
import com.proyecto_gym.domain.Rol;
import com.proyecto_gym.domain.User;
import com.proyecto_gym.service.RolService;
import com.proyecto_gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = userService.getUsers(false);
        model.addAttribute("usuarios", lista);
        model.addAttribute("totalUsuarios", lista.size());

        // Ensure roles are added to the model
        Set<Rol> uniqueRoles = lista.stream()
                .map(User::getRol)
                .collect(Collectors.toSet());
        model.addAttribute("roles", uniqueRoles);

        return "/usuario/listado";
    }

    @GetMapping("/listado/{idRol}")
    public String listado(Model model, Rol rol) {
        rol = rolService.getRol(rol);
        model.addAttribute("usuarios", rol.getUsuarios());
        var roles = rolService.getRoles(false);
        model.addAttribute("roles", roles);
        return "/usuario/listado";
    }


    @GetMapping("/eliminar/{idUsuario}")
    public String eliminar(User user) {
        userService.delete(user);
        return "redirect:/usuario/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idUsuario}")
    public String modificar(User user, Model model) {
        user = userService.getUser(user);
        model.addAttribute("usuario", user);
        return "/usuario/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") User usuario) {
        // Ensure the rol is set correctly
        if (usuario.getRol() == null || usuario.getRol().getIdRol() == 0) {
            // Set a default role if not provided
            Rol defaultRol = new Rol();
            defaultRol.setIdRol(2); // Assuming 2 is the default role ID for "Usuario"
            usuario.setRol(defaultRol);
        }
        userService.save(usuario);
        return "redirect:/usuario/listado";
    }
}
