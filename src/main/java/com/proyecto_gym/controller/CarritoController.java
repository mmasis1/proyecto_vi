package com.proyecto_gym.controller;

import com.proyecto_gym.domain.Item;
import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.service.ItemService;
import com.proyecto_gym.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/agregar/{idProducto}")
    public ModelAndView agregar(@PathVariable("idProducto") int idProducto, Model model) {
        Producto p = productoService.getProductoPorId(idProducto);
        if (p == null) {
            return new ModelAndView("redirect:/"); // O mostrar error
        }

        Item item2 = new Item(p);
        itemService.save(item2);

        var lista = itemService.getItems();
        var totalCompra = itemService.getTotal();

        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        model.addAttribute("totalProductos", lista.size());

        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = itemService.getItems();
        model.addAttribute("listaItems", lista);
        return "/carrito/listado";
    }
}
