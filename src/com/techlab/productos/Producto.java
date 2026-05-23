package com.techlab.productos;
 
public class Producto {
 
    private static int contadorId = 1;
 
    private int id;
    private String nombre;
    private double precio;
    private int stock;
 
    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
 
    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
 
    // Setters con validacion
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }
 
    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }
 
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }
 
    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
 
    public String getTipo() {
        return "Producto";
    }
 
    @Override
    public String toString() {
        return String.format("[ID: %d] %s (%s) | Precio: $%.2f | Stock: %d",
                id, nombre, getTipo(), precio, stock);
    }
}
