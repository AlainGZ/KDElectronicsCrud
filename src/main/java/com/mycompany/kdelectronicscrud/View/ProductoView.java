package com.mycompany.kdelectronicscrud.View;

import com.mycompany.kdelectronicscrud.Controller.ProductoController;
import com.mycompany.kdelectronicscrud.Model.Producto;
import java.util.Scanner;

/**
 *
 * @author Alain Gomez Zapata
 */
public class ProductoView {
    
    private ProductoController productoController;

    public ProductoView() {
        productoController = new ProductoController();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Registrar Producto ");
            System.out.println("2. Consultar Producto por Código");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Listar Todos los Productos");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarProducto(scanner);
                    break;
                case 2:
                    consultarProducto(scanner);
                    break;
                case 3:
                    actualizarProducto(scanner);
                    break;
                case 4:
                    eliminarProducto(scanner);
                    break;
                case 5:
                    listarProductos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarProducto(Scanner scanner) {
        System.out.println("Ingrese los datos del producto:");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio base: ");
        double precioBase = scanner.nextDouble();
        System.out.print("Precio de venta: ");
        double precioVenta = scanner.nextDouble();
        scanner.nextLine();  
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Cantidad disponible: ");
        int cantidad = scanner.nextInt();

        Producto producto = new Producto();
        producto.setCodigoProducto(codigo);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioBase(precioBase);
        producto.setPrecioVenta(precioVenta);
        producto.setCategoria(categoria);
        producto.setCantidadDisponible(cantidad);

        productoController.crearProducto(producto);
        System.out.println("Producto registrado correctamente.");
    }

    private void consultarProducto(Scanner scanner) {
        System.out.print("Ingrese el código del producto: ");
        int codigo = scanner.nextInt();
        Producto producto = productoController.obtenerProductoPorCodigo(codigo);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println("Código: " + producto.getCodigoProducto());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio base: " + producto.getPrecioBase());
            System.out.println("Precio venta: " + producto.getPrecioVenta());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("Cantidad disponible: " + producto.getCantidadDisponible());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void actualizarProducto(Scanner scanner) {
        System.out.print("Ingrese el código del producto a actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();  
        Producto producto = productoController.obtenerProductoPorCodigo(codigo);

        if (producto != null) {
            System.out.println("Ingrese los nuevos datos del producto:");
            System.out.print("Nombre (" + producto.getNombre() + "): ");
            producto.setNombre(scanner.nextLine());
            System.out.print("Descripción (" + producto.getDescripcion() + "): ");
            producto.setDescripcion(scanner.nextLine());
            System.out.print("Precio base (" + producto.getPrecioBase() + "): ");
            producto.setPrecioBase(scanner.nextDouble());
            System.out.print("Precio de venta (" + producto.getPrecioVenta() + "): ");
            producto.setPrecioVenta(scanner.nextDouble());
            scanner.nextLine();  
            System.out.print("Categoría (" + producto.getCategoria() + "): ");
            producto.setCategoria(scanner.nextLine());
            System.out.print("Cantidad disponible (" + producto.getCantidadDisponible() + "): ");
            producto.setCantidadDisponible(scanner.nextInt());

            productoController.actualizarProducto(producto);
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void eliminarProducto(Scanner scanner) {
        System.out.print("Ingrese el código del producto a eliminar: ");
        int codigo = scanner.nextInt();
        productoController.eliminarProducto(codigo);
        System.out.println("Producto eliminado (lógicamente).");
    }

    private void listarProductos() {
        System.out.println("Listado de productos:");
        for (Producto producto : productoController.obtenerTodosLosProductos()) {
            System.out.println("Código: " + producto.getCodigoProducto() +
                    ", Nombre: " + producto.getNombre() +
                    ", Precio venta: " + producto.getPrecioVenta() +
                    ", Cantidad: " + producto.getCantidadDisponible());
        }
    }
}
