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
@RequestMapping("/sobrenosotros")
public class SobreNosotrosController {
    @Autowired
    private SobreNosotrosService sobrenosotrosService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = sobrenosotrosService.getSobreNosotross(false);
        model.addAttribute("sobrenosotross", lista);
        model.addAttribute("totalSobreNosotross", lista.size());
        return "/sobrenosotros/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/eliminar/{idInfo}")
    public String eliminar(SobreNosotros sobrenosotros) {
        sobrenosotrosService.delete(sobrenosotros);
        return "redirect:/sobrenosotros/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idInfo}")
    public String modificar(SobreNosotros sobrenosotros, Model model) {
        sobrenosotros = sobrenosotrosService.getSobreNosotros(sobrenosotros);
        model.addAttribute("sobrenosotros", sobrenosotros);
        return "/sobrenosotros/modifica"; // Refers to the method listado
    }


    @PostMapping("/guardar")
    public String guardar(SobreNosotros sobrenosotros) {
        sobrenosotrosService.save(sobrenosotros);
        return "redirect:/sobrenosotros/listado"; // Refers to the method listado
    }
}
