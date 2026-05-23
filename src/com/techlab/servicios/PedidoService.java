package com.techlab.servicios;
 
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
 
import java.util.ArrayList;
import java.util.Scanner;
 
public class PedidoService {
 
    private ArrayList<Pedido> pedidos = new ArrayList<>();
 
    public void crearPedido(ProductoService productoService, Scanner scanner) {
        if (productoService.getProductos().isEmpty()) {
            System.out.println("No hay productos disponibles para crear un pedido.");
            return;
        }
 
        Pedido pedido = new Pedido();
        boolean agregando = true;
 
        System.out.println("\n====== NUEVO PEDIDO ======");
 
        while (agregando) {
            productoService.listarProductos();
            System.out.print("Ingrese el ID del producto a agregar (0 para terminar): ");
            try {
                int idProducto = Integer.parseInt(scanner.nextLine().trim());
 
                if (idProducto == 0) {
                    agregando = false;
                    continue;
                }
 
                Producto producto = productoService.buscarPorId(idProducto);
                if (producto == null) {
                    System.out.println("✘ Producto no encontrado.");
                    continue;
                }
 
                System.out.print("Cantidad deseada (stock disponible: " + producto.getStock() + "): ");
                int cantidad = Integer.parseInt(scanner.nextLine().trim());
 
                if (cantidad <= 0) {
                    System.out.println("✘ La cantidad debe ser mayor a cero.");
                    continue;
                }
 
                // Lanza excepcion personalizada si no hay suficiente stock
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException(producto.getNombre(), producto.getStock(), cantidad);
                }
 
                pedido.agregarLinea(new LineaPedido(producto, cantidad));
                System.out.println("✔ Producto agregado al pedido.");
 
            } catch (NumberFormatException e) {
                System.out.println("✘ Entrada invalida. Ingrese un numero entero.");
            } catch (StockInsuficienteException e) {
                System.out.println("✘ " + e.getMessage());
            }
        }
 
        if (pedido.getLineas().isEmpty()) {
            System.out.println("Pedido cancelado (sin productos).");
            return;
        }
 
        System.out.println("\nResumen del pedido:");
        System.out.println(pedido);
 
        System.out.print("\n¿Confirmar pedido? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
 
        if (confirmacion.equals("s")) {
            // Descontar stock de cada producto
            for (LineaPedido linea : pedido.getLineas()) {
                linea.getProducto().reducirStock(linea.getCantidad());
            }
            pedidos.add(pedido);
            System.out.println("✔ Pedido #" + pedido.getId() + " confirmado correctamente.");
        } else {
            System.out.println("Pedido descartado.");
        }
    }
 
    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n====== PEDIDOS REALIZADOS ======");
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println();
        }
    }
}
