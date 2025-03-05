package com.proyecto_gym.controller;

import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.domain.User;
import com.proyecto_gym.service.FirebaseStorageService;
import com.proyecto_gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = userService.getUsers(false);
        model.addAttribute("usuarios", lista);
        model.addAttribute("totalUsuarios", lista.size());
        return "/usuario/listado"; // Refers to templates/categories.html
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
    public String guardar(User user) {

        userService.save(user);
        return "redirect:/usuario/listado"; // Refers to the method listado
    }
}
