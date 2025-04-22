
package com.proyecto_gym.service;

import com.proyecto_gym.domain.Item;
 import jakarta.servlet.http.HttpSession;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Objects;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class ItemService {
 
     //Se utiliza una variable de session para guardar una lista
     @Autowired
     private HttpSession session;
 
     //El siguiente método crea un item en la variable de session
     //Si la variable no existe se crea ...
     public void save(Item item) {
         //Se recupera la variable de session
         @SuppressWarnings("unchecked")
         List<Item> lista = (List) session.getAttribute("listaItems");
 
         //Se valida si la lista ya estaba como variable de session
         if (lista == null) {
             lista = new ArrayList<>();
         }
         
         
         //Se busca si el idProducto ya esta en la lista..
         boolean existe=false;
         for (Item i : lista){
             if (Objects.equals(item.getIdProducto(), i.getIdProducto())){
                 existe=true;
                  if (i.getCantidad() < i.getStock()) {
                 i.setCantidad(i.getCantidad()+1);
                 
                 }
                 break;
             }
         }
         if (!existe) { //si no estaba en la lista se crea en ella ...
             item.setCantidad (1);
             lista.add (item);
         }
         session.setAttribute("listaItems", lista);
     }
     //El siguiente método recupera un item de la variable de session
     //Si no está, retorna null
     public Item getItem(Item item) {
         //Se recupera la variable de session
         @SuppressWarnings("unchecked")
         List<Item> lista = (List) session.getAttribute("listaItems");
         //Se valida si la lista ya estaba como variable de session
         if (lista == null) {
            return null;
         }
         //Se busca si el idProducto ya esta en la lista..
         for (Item i : lista){
             if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                 return i;
             }
 
         }
         return null;
     }
  //El siguiente método recupera el total de compra segun la lista
 public double getTotal() {
         //Se recupera la variable de session
         @SuppressWarnings("unchecked")
         List<Item> lista = (List) session.getAttribute("listaItems");
         //Se valida si la lista ya estaba como variable de session
         if (lista == null) {
            return 0;
         }
         //Se busca si el idProducto ya esta en la lista..
         double total=0;
         for (Item i : lista){
            total+=i.getCantidad()*i.getPrecio();
         }
         return total;
     }
 
 //El siguiente método recupera la lista completa desde la variable de session
     public List<Item> getItems() {
         //Se recupera la variable de session
         @SuppressWarnings("unchecked")
         List<Item> lista = (List) session.getAttribute("listaItems");
         return lista;
     }
 
 }