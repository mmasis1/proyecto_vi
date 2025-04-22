package com.proyecto_gym.domain;

public class Item  extends Producto{
     private int cantidad;
 
     public int getCantidad() {
         return cantidad;
     }
 
     public void setCantidad(int cantidad) {
         this.cantidad = cantidad;
     }
 
     public Item() {
     }
     
     public Item(Producto p) {
         super.setEstado(p.isEstado());                 
        super.setCategoria(p.getCategoria());
        super.setDescripcion(p.getDescripcion());
        super.setNombre(p.getNombre());                
        super.setStock(p.getStock());                 
        super.setIdProducto(p.getIdProducto());
        super.setPrecio(p.getPrecio());
        super.setImagen(p.getImagen());                
        this.cantidad = 0;
     }
 
 }