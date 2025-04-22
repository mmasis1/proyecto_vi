package com.proyecto_gym.controller;

import com.proyecto_gym.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturar")
public class FacturarController {

    @Autowired
    private ItemService itemService;

    // ✅ Para "facturar" los productos del carrito (por ahora, solo limpia el carrito)
    @GetMapping("/carrito")
    public String facturarCarrito() {
        itemService.facturar(); // este método lo tenés que implementar si no existe
        return "redirect:/";
    }
}
