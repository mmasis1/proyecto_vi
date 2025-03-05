package com.proyecto_gym.controller;

import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.domain.Rol;
import com.proyecto_gym.service.FirebaseStorageService;
import com.proyecto_gym.service.ProductoService;
import com.proyecto_gym.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = rolService.getRoles(false);
        model.addAttribute("roles", lista);
        model.addAttribute("totalRoles", lista.size());
        return "/rol/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/eliminar/{idRol}")
    public String eliminar(Rol rol) {
        rolService.delete(rol);
        return "redirect:/rol/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idRol}")
    public String modificar(Rol rol, Model model) {
        rol = rolService.getRol(rol);
        model.addAttribute("rol", rol);
        return "/rol/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(Rol rol) {
        rolService.save(rol);
        return "redirect:/rol/listado";
    }
}
