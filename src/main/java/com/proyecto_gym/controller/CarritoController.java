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
import org.springframework.web.bind.annotation.PostMapping;
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
        var totalCompra = itemService.getTotal();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        return "/carrito/listado";
    }

    //Eliminar un item del carrito
    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Model model, Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    //Cargar datos para modificar cantidad de un producto
    @GetMapping("/modificar/{idProducto}")
    public String modificar(Model model, Item item) {
        item = itemService.getItem(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    //Guardar los cambios realizados a la cantidad
    @PostMapping("/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }
}
