
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoDTO {
    
    private EmpleadoDAO edao = new EmpleadoDAO();
    private Empleado e = new Empleado();
    
    public List listarEmpleado() {
        
        List listaE = edao.listar();
        
        return listaE;
    }
    
    public void agregarEmpleado (String dni, String nom, String tel, String est, String user) {
        e.setDni(dni);
        e.setNom(nom);
        e.setTel(tel);
        e.setEstado(est);
        e.setUser(user);
        edao.agregar(e);
    }
    
    public Empleado editarEmpleado (int id) {
        e = edao.listarId(id);
        return e;
    }
    
    public void actualizarEmpleado (String dni, String nom, String tel, String est, String user, int id) {
        e.setDni(dni);
        e.setNom(nom);
        e.setTel(tel);
        e.setEstado(est);
        e.setUser(user);
        e.setId(id);
        edao.actualizar(e);        
    }
    
    public void eliminarEmpleado (int id) {
        
        edao.delete(id);
    }
}
