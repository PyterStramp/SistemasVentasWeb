
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoDTO {
    
    private List<Producto>listaProducto = new ArrayList<>();
    
    public List listarProducto() {
        
        
        return listaProducto;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
}
