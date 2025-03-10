package com.proyecto_gym.controller;

import com.proyecto_gym.domain.SobreNosotros;
import com.proyecto_gym.service.SobreNosotrosService;
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
@RequestMapping("/sobreNosotros")
public class SobreNosotrosController {
    @Autowired
    private SobreNosotrosService sobrenosotrosService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = sobrenosotrosService.getSobreNosotross(false);
        model.addAttribute("sobreNosotross", lista);
        model.addAttribute("totalSobreNosotross", lista.size());
        return "/sobreNosotros/listado"; // Refers to templates/sobreNosotros.html
    }

    @GetMapping("/eliminar/{idInfo}")
    public String eliminar(SobreNosotros sobreNosotros) {
        sobrenosotrosService.delete(sobreNosotros);
        return "redirect:/sobreNosotros/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idInfo}")
    public String modificar(SobreNosotros sobreNosotros, Model model) {
        sobreNosotros = sobrenosotrosService.getSobreNosotros(sobreNosotros);
        model.addAttribute("sobreNosotros", sobreNosotros);
        return "/sobreNosotros/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(SobreNosotros sobreNosotros) {
        sobrenosotrosService.save(sobreNosotros);
        return "redirect:/sobreNosotros/listado"; // Refers to the method listado
    }
}
