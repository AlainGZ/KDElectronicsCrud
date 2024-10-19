
package com.mycompany.kdelectronicscrud.Dao;

import com.mycompany.kdelectronicscrud.Model.Producto;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alain Gomez Zapata
 */
public class ProductoDao {
   
    private final String url = "jdbc:mysql://localhost:3306/inventario";
    private final String usuario = "root";
    private final String password = "admin";
    
    public void crearProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO productos (codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad_disponible, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, producto.getCodigoProducto());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecioBase());
            statement.setDouble(5, producto.getPrecioVenta());
            statement.setString(6, producto.getCategoria());
            statement.setInt(7, producto.getCantidadDisponible());
            statement.setBoolean(8, true); 
            statement.executeUpdate();
        }
    }

    public Producto obtenerProductoPorCodigo(int codigoProducto) throws SQLException {
        String query = "SELECT * FROM productos WHERE codigo_producto = ? AND activo = true";
        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, codigoProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(resultSet.getInt("codigo_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecioBase(resultSet.getDouble("precio_base"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setCategoria(resultSet.getString("categoria"));
                producto.setCantidadDisponible(resultSet.getInt("cantidad_disponible"));
                producto.setActivo(resultSet.getBoolean("activo"));
                return producto;
            }
        }
        return null;
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio_base = ?, precio_venta = ?, categoria = ?, cantidad_disponible = ? WHERE codigo_producto = ? AND activo = true";
        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecioBase());
            statement.setDouble(4, producto.getPrecioVenta());
            statement.setString(5, producto.getCategoria());
            statement.setInt(6, producto.getCantidadDisponible());
            statement.setInt(7, producto.getCodigoProducto());
            statement.executeUpdate();
        }
    }

    public void eliminarProducto(int codigoProducto) throws SQLException {
        String query = "UPDATE productos SET activo = false WHERE codigo_producto = ?";
        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, codigoProducto);
            statement.executeUpdate();
        }
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE activo = true";
        try (Connection connection = DriverManager.getConnection(url, usuario, password);
            java.sql.Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(resultSet.getInt("codigo_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecioBase(resultSet.getDouble("precio_base"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setCategoria(resultSet.getString("categoria"));
                producto.setCantidadDisponible(resultSet.getInt("cantidad_disponible"));
                producto.setActivo(resultSet.getBoolean("activo"));
                productos.add(producto);
            }
        }
        return productos;
    }
    
}
