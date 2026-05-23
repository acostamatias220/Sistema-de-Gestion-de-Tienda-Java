package com.techlab;
 
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import com.techlab.servicios.PedidoService;
import com.techlab.servicios.ProductoService;
 
import java.util.Scanner;
 
public class Main {
 
    private static ProductoService productoService = new ProductoService();
    private static PedidoService pedidoService = new PedidoService();
    private static Scanner scanner = new Scanner(System.in);
 
    public static void main(String[] args) {
 
        // Productos de ejemplo para no empezar vacio
        productoService.agregarProducto(new Comida("Cafe Premium", 1500.0, 20, "12/2025"));
        productoService.agregarProducto(new Bebida("Agua Mineral", 800.0, 50, 1.5));
        productoService.agregarProducto(new Producto("Servilletas", 300.0, 100));
 
        boolean corriendo = true;
 
        while (corriendo) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
 
            switch (opcion) {
                case "1":
                    agregarProducto();
                    break;
                case "2":
                    productoService.listarProductos();
                    break;
                case "3":
                    buscarYActualizarProducto();
                    break;
                case "4":
                    eliminarProducto();
                    break;
                case "5":
                    pedidoService.crearPedido(productoService, scanner);
                    break;
                case "6":
                    pedidoService.listarPedidos();
                    break;
                case "7":
                    System.out.println("Hasta luego.");
                    corriendo = false;
                    break;
                default:
                    System.out.println("✘ Opcion invalida. Intente de nuevo.");
            }
        }
 
        scanner.close();
    }
 
    private static void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       SISTEMA DE TIENDA      ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Agregar producto         ║");
        System.out.println("║  2. Listar productos         ║");
        System.out.println("║  3. Buscar/Actualizar        ║");
        System.out.println("║  4. Eliminar producto        ║");
        System.out.println("║  5. Crear pedido             ║");
        System.out.println("║  6. Listar pedidos           ║");
        System.out.println("║  7. Salir                    ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opcion: ");
    }
 
    private static void agregarProducto() {
        System.out.println("\n-- AGREGAR PRODUCTO --");
        System.out.println("Tipo: 1) Producto generico  2) Comida  3) Bebida");
        System.out.print("Seleccione tipo: ");
        String tipo = scanner.nextLine().trim();
 
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("✘ El nombre no puede estar vacio.");
                return;
            }
 
            System.out.print("Precio: $");
            double precio = Double.parseDouble(scanner.nextLine().trim());
 
            System.out.print("Stock inicial: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());
 
            if (precio < 0 || stock < 0) {
                System.out.println("✘ Precio y stock no pueden ser negativos.");
                return;
            }
 
            switch (tipo) {
                case "2":
                    System.out.print("Fecha de vencimiento (dd/mm/aaaa): ");
                    String fecha = scanner.nextLine().trim();
                    productoService.agregarProducto(new Comida(nombre, precio, stock, fecha));
                    break;
                case "3":
                    System.out.print("Volumen en litros: ");
                    double volumen = Double.parseDouble(scanner.nextLine().trim());
                    productoService.agregarProducto(new Bebida(nombre, precio, stock, volumen));
                    break;
                default:
                    productoService.agregarProducto(new Producto(nombre, precio, stock));
            }
 
        } catch (NumberFormatException e) {
            System.out.println("✘ Valor numerico invalido. Intente de nuevo.");
        }
    }
 
    private static void buscarYActualizarProducto() {
        System.out.println("\n-- BUSCAR PRODUCTO --");
        System.out.print("Ingrese nombre o ID del producto: ");
        String entrada = scanner.nextLine().trim();
 
        Producto producto = null;
 
        try {
            int id = Integer.parseInt(entrada);
            producto = productoService.buscarPorId(id);
        } catch (NumberFormatException e) {
            producto = productoService.buscarPorNombre(entrada);
        }
 
        if (producto == null) {
            System.out.println("✘ Producto no encontrado.");
            return;
        }
 
        System.out.println("Producto encontrado: " + producto);
        System.out.println("¿Desea actualizar? 1) Precio  2) Stock  3) No actualizar");
        System.out.print("Opcion: ");
        String opcion = scanner.nextLine().trim();
 
        try {
            if (opcion.equals("1")) {
                System.out.print("Nuevo precio: $");
                double nuevoPrecio = Double.parseDouble(scanner.nextLine().trim());
                producto.setPrecio(nuevoPrecio);
                System.out.println("✔ Precio actualizado.");
            } else if (opcion.equals("2")) {
                System.out.print("Nuevo stock: ");
                int nuevoStock = Integer.parseInt(scanner.nextLine().trim());
                producto.setStock(nuevoStock);
                System.out.println("✔ Stock actualizado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✘ Valor invalido.");
        } catch (IllegalArgumentException e) {
            System.out.println("✘ " + e.getMessage());
        }
    }
 
    private static void eliminarProducto() {
        System.out.println("\n-- ELIMINAR PRODUCTO --");
        productoService.listarProductos();
        System.out.print("Ingrese el ID del producto a eliminar: ");
 
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Producto producto = productoService.buscarPorId(id);
 
            if (producto == null) {
                System.out.println("✘ Producto no encontrado.");
                return;
            }
 
            System.out.print("¿Confirmar eliminacion de '" + producto.getNombre() + "'? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();
 
            if (confirmacion.equals("s")) {
                productoService.eliminarPorId(id);
                System.out.println("✔ Producto eliminado.");
            } else {
                System.out.println("Eliminacion cancelada.");
            }
 
        } catch (NumberFormatException e) {
            System.out.println("✘ ID invalido.");
        }
    }
}