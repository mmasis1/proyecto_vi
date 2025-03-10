package com.proyecto_gym.controller;

import com.proyecto_gym.domain.PlanMembresia;
import com.proyecto_gym.service.PlanMembresiaService;
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
@RequestMapping("/planMembresia")
public class PlanMembresiaController {
    @Autowired
    private PlanMembresiaService planMembresiaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = planMembresiaService.getPlanMembresias(false);
        model.addAttribute("planMembresias", lista);
        model.addAttribute("totalPlanMembresias", lista.size());
        return "/planMembresia/listado"; // Refers to templates/planMembresia.html
    }

    @GetMapping("/eliminar/{idPlan}")
    public String eliminar(PlanMembresia planMembresia) {
        planMembresiaService.delete(planMembresia);
        return "redirect:/planMembresia/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idPlan}")
    public String modificar(PlanMembresia planMembresia, Model model) {
        planMembresia = planMembresiaService.getPlanMembresia(planMembresia);
        model.addAttribute("planMembresia", planMembresia);
        return "/planMembresia/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(PlanMembresia planMembresia) {
        planMembresiaService.save(planMembresia);
        return "redirect:/planMembresia/listado"; // Refers to the method listado
    }
}