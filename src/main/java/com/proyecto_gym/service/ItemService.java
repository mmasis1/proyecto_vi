package com.proyecto_gym.service;

import com.proyecto_gym.domain.Factura;
import com.proyecto_gym.domain.Item;
import com.proyecto_gym.domain.Producto;
import com.proyecto_gym.domain.User;
import com.proyecto_gym.domain.Venta;
import com.proyecto_gym.repository.FacturaRepository;
import com.proyecto_gym.repository.ProductoRepository;
import com.proyecto_gym.repository.UserRepository;
import com.proyecto_gym.repository.VentaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public void save(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        if (lista == null) {
            lista = new ArrayList<>();
        }

        boolean existe = false;
        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                existe = true;
                if (i.getCantidad() < i.getStock()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }

        if (!existe) {
            item.setCantidad(1);
            lista.add(item);
        }

        session.setAttribute("listaItems", lista);
    }

    public Item getItem(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        if (lista == null) return null;

        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                return i;
            }
        }

        return null;
    }

    public double getTotal() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        if (lista == null) return 0;

        double total = 0;
        for (Item i : lista) {
            total += i.getCantidad() * i.getPrecio();
        }
        return total;
    }

    public List<Item> getItems() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        return lista;
    }

    public void delete(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        if (lista != null) {
            lista.removeIf(i -> Objects.equals(i.getIdProducto(), item.getIdProducto()));
            session.setAttribute("listaItems", lista);
        }
    }

    public void update(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List<Item>) session.getAttribute("listaItems");
        if (lista != null) {
            for (Item i : lista) {
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                    i.setCantidad(item.getCantidad());
                    break;
                }
            }
        }
    }

    public void facturar() {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else if (principal != null) {
            username = principal.toString();
        }

        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        User user = userRepository.findByNombre(username); 
        if (user == null) {
            System.out.println("Usuario no existe en la base de datos...");
            return;
        }

        Factura factura = new Factura(user.getIdUsuario()); // ✅

        factura = facturaRepository.save(factura);

        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                Producto producto = productoRepository.getReferenceById(i.getIdProducto());
                if (producto.getStock() >= i.getCantidad()) {
                    Venta venta = new Venta(
                            factura.getIdFactura(),
                            (long) i.getIdProducto(),
                            i.getPrecio(),
                            i.getCantidad()
                    );
                    ventaRepository.save(venta);

                    producto.setStock(producto.getStock() - i.getCantidad());
                    productoRepository.save(producto);

                    total += i.getCantidad() * i.getPrecio();
                }
            }

            factura.setTotal(total);
            facturaRepository.save(factura);

            listaItems.clear();
            session.setAttribute("listaItems", listaItems);
        }
    }
}
