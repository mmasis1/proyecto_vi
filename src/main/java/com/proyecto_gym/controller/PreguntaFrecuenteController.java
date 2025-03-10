package com.proyecto_gym.controller;

import com.proyecto_gym.domain.PreguntaFrecuente;
import com.proyecto_gym.service.PreguntaFrecuenteService;
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
@RequestMapping("/preguntaFrecuente")
public class PreguntaFrecuenteController {
    @Autowired
    private PreguntaFrecuenteService preguntaFrecuenteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = preguntaFrecuenteService.getPreguntaFrecuentes(false);
        model.addAttribute("preguntaFrecuentes", lista);
        model.addAttribute("totalPreguntaFrecuentes", lista.size());
        return "/preguntaFrecuente/listado";
    }

    @GetMapping("/eliminar/{idPregunta}")
    public String eliminar(PreguntaFrecuente preguntaFrecuente) {
        preguntaFrecuenteService.delete(preguntaFrecuente);
        return "redirect:/preguntaFrecuente/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idPregunta}")
    public String modificar(PreguntaFrecuente preguntaFrecuente, Model model) {
        preguntaFrecuente = preguntaFrecuenteService.getPreguntaFrecuente(preguntaFrecuente);
        model.addAttribute("preguntaFrecuente", preguntaFrecuente);
        return "/preguntaFrecuente/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(PreguntaFrecuente preguntaFrecuente) {
        preguntaFrecuenteService.save(preguntaFrecuente);
        return "redirect:/preguntaFrecuente/listado"; // Refers to the method listado
    }
}
