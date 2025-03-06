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
@RequestMapping("/preguntafrecuente")
public class PreguntaFrecuenteController {
    @Autowired
    private PreguntaFrecuenteService preguntafrecuenteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = preguntafrecuenteService.getPreguntaFrecuentes(false);
        model.addAttribute("preguntafrecuentes", lista);
        model.addAttribute("totalPreguntaFrecuentes", lista.size());
        return "/preguntafrecuente/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/eliminar/{idPreguntaFrecuente}")
    public String eliminar(PreguntaFrecuente preguntafrecuente) {
        preguntafrecuenteService.delete(preguntafrecuente);
        return "redirect:/preguntafrecuente/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idPreguntaFrecuente}")
    public String modificar(PreguntaFrecuente preguntafrecuente, Model model) {
        preguntafrecuente = preguntafrecuenteService.getPreguntaFrecuente(preguntafrecuente);
        model.addAttribute("preguntafrecuente", preguntafrecuente);
        return "/preguntafrecuente/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(PreguntaFrecuente preguntafrecuente) {
        preguntafrecuenteService.save(preguntafrecuente);
        return "redirect:/preguntafrecuente/listado"; // Refers to the method listado
    }
}
