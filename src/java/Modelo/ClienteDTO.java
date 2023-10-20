package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    private ClienteDAO cdao = new ClienteDAO();
    private Cliente c = new Cliente();

    public List listarCliente() {

        List listaE = cdao.listar();

        return listaE;
    }

    public void agregarCliente(String dni, String nom, String dir, String est) {
        c.setDni(dni);
        c.setNom(nom);
        c.setDir(dir);
        c.setEstado(est);
        cdao.agregar(c);
    }

    public Cliente editarCliente(int id) {
        c = cdao.listarId(id);
        return c;
    }

    public void actualizarCliente(String dni, String nom, String dir, String est, int id) {
        c.setDni(dni);
        c.setNom(nom);
        c.setDir(dir);
        c.setEstado(est);
        c.setId(id);
        cdao.actualizar(c);
    }

    public void eliminarCliente(int id) {
        cdao.delete(id);
    }
}
