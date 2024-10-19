
package com.mycompany.kdelectronicscrud.Controller;

import com.mycompany.kdelectronicscrud.Dao.ProductoDao;
import com.mycompany.kdelectronicscrud.Model.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alain Gomez Zapata
 */
public class ProductoController {
    private ProductoDao productoDao;

    public ProductoController() {
        productoDao = new ProductoDao();
    }

    public void crearProducto(Producto producto) {
        try {
            productoDao.crearProducto(producto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto obtenerProductoPorCodigo(int codigo) {
        try {
            return productoDao.obtenerProductoPorCodigo(codigo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarProducto(Producto producto) {
        try {
            productoDao.actualizarProducto(producto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int codigoProducto) {
        try {
            productoDao.eliminarProducto(codigoProducto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        try {
            return productoDao.obtenerTodosLosProductos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
