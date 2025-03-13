package com.proyecto_gym.controller;

import com.proyecto_gym.domain.FormularioContacto;
import com.proyecto_gym.service.FormularioContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/formularioContacto")
public class FormularioContactoController {

    @Autowired
    private FormularioContactoService formularioContactoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = formularioContactoService.getFormularios();
        model.addAttribute("formularios", lista);
        model.addAttribute("totalFormularios", lista.size());
        return "/formularioContacto/listado"; // Referencia a templates/contacto/listado.html
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id) {
        formularioContactoService.delete(id);  // Eliminamos usando solo el id
        return "redirect:/formularioContacto/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") int id, Model model) {
        FormularioContacto formularioContacto = formularioContactoService.getFormularioPorId(id);  // Obtenemos el formulario por ID
        model.addAttribute("formularioContacto", formularioContacto);
        return "/formularioContacto/modifica"; // Referencia a templates/contacto/modifica.html
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("formularioContacto") FormularioContacto formularioContacto) {
        formularioContactoService.save(formularioContacto);
        return "redirect:/formularioContacto/listado"; // Redirige a la lista de formularios
}
}