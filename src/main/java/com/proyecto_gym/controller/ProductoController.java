package com.proyecto_gym.controller;

import com.proyecto_gym.domain.Categoria;
import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.service.CategoriaService;
import com.proyecto_gym.service.FirebaseStorageService;
import com.proyecto_gym.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/producto/listado"; // Refers to templates/categories.html
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("productos", categoria.getProductos());
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado"; // Refers to the method listado
    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica"; // Refers to the method listado
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setImagen(ruta);
        }
        productoService.save(producto);
        return "redirect:/producto/listado"; // Refers to the method listado  
    }
}
