package com.techlab.servicios;
 
import com.techlab.productos.Producto;
import java.util.ArrayList;
 
public class ProductoService {
 
    private ArrayList<Producto> productos = new ArrayList<>();
 
    public void agregarProducto(Producto p) {
        productos.add(p);
        System.out.println("✔ Producto '" + p.getNombre() + "' agregado con ID " + p.getId());
    }
 
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n====== LISTA DE PRODUCTOS ======");
        for (Producto p : productos) {
            System.out.println(p);
        }
        System.out.println("================================");
    }
 
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }
 
    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }
 
    public boolean eliminarPorId(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }
 
    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
