# Sistema de Gestión de Tienda — Java

Aplicación de consola desarrollada en Java que permite administrar el inventario y pedidos de una tienda de forma interactiva.

## Tecnologías

- Java 17
- POO — herencia, encapsulamiento, excepciones personalizadas

## Funcionalidades

- Agregar productos genéricos, comidas y bebidas
- Listar y buscar productos por nombre o ID
- Actualizar precio y stock
- Eliminar productos con confirmación
- Crear y listar pedidos
- Validaciones de stock insuficiente con excepciones personalizadas

## Estructura del proyecto
src/
└── com/techlab/
├── productos/       # Producto, Comida, Bebida
├── servicios/       # ProductoService, PedidoService
├── pedidos/         # Pedido, LineaPedido
├── excepciones/     # StockInsuficienteException
└── Main.java

## Cómo ejecutar

Abrí el proyecto en VS Code o IntelliJ y ejecutá `Main.java`.