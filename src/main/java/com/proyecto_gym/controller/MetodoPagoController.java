package com.proyecto_gym.controller;

import com.proyecto_gym.domain.MetodoPago;
import com.proyecto_gym.service.MetodoPagoService;
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
@RequestMapping("/metodoPago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = metodoPagoService.getMetodoPagos(false);
        model.addAttribute("metodoPagos", lista);
        model.addAttribute("totalMetodoPagos", lista.size());
        return "/metodoPago/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/eliminar/{idMetodoPago}")
    public String eliminar(MetodoPago metodoPago) {
        metodoPagoService.delete(metodoPago);
        return "redirect:/metodoPago/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idMetodoPago}")
    public String modificar(MetodoPago metodoPago, Model model) {
        metodoPago = metodoPagoService.getMetodoPago(metodoPago);
        model.addAttribute("metodoPago", metodoPago);
        return "/metodoPago/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(MetodoPago metodoPago) {
        metodoPagoService.save(metodoPago);
        return "redirect:/metodoPago/listado"; // Refers to the method listado
    }
}
