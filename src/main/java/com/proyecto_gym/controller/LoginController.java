package com.proyecto_gym.controller;

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
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/listado")
    public String listado(Model model) {
        return "/login/listado"; // Refers to templates/pruebas.html
    }

}