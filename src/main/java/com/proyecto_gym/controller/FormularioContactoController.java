package com.proyecto_gym.controller;

import com.proyecto_gym.domain.FormularioContacto;
import com.proyecto_gym.service.FormularioContactoService;
import com.proyecto_gym.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/formularioContacto")
public class FormularioContactoController {
    @Autowired
    private FormularioContactoService formularioContactoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = formularioContactoService.getFormularioContactos(false);
        model.addAttribute("formularioContactos", lista);
        model.addAttribute("totalFormularioContactos", lista.size());
        return "/formularioContacto/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/eliminar/{idContacto}")
    public String eliminar(FormularioContacto formularioContacto) {
        formularioContactoService.delete(formularioContacto);
        return "redirect:/formularioContacto/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idContacto}")
    public String modificar(FormularioContacto formularioContacto, Model model) {
        formularioContacto = formularioContactoService.getFormularioContacto(formularioContacto);
        model.addAttribute("formularioContacto", formularioContacto);
        return "/formularioContacto/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(FormularioContacto formularioContacto) {
        formularioContactoService.save(formularioContacto);
        return "redirect:/formularioContacto/listado"; // Refers to the method listado
    }
}
