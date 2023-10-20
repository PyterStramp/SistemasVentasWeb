package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoDTO {

    private ProductoDAO pdao = new ProductoDAO();
    private Producto p = new Producto();

    public List listarProducto() {
        List listaE = pdao.listar();
        return listaE;
    }

    public void agregarProductoe(String nom, String precio, String stock, String est) {
        p.setNom(nom);
        p.setPrecio(Double.parseDouble(precio));
        p.setStock(Integer.parseInt(stock));
        p.setEstado(est);
        pdao.agregar(p);
    }

    public Producto editarProducto(int id) {
        p = pdao.listarId(id);
        return p;
    }

    public void actualizarProducto(String nom, String precio, String stock, String est, int id) {
        p.setNom(nom);
        p.setPrecio(Double.parseDouble(precio));
        p.setStock(Integer.parseInt(stock));
        p.setEstado(est);
        p.setId(id);
        pdao.actualizar(p);
    }

    public void eliminarProducto(int id) {
        pdao.delete(id);
    }

}
