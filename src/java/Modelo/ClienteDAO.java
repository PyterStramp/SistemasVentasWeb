package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Cliente buscar (String dni) {
        Cliente c = new Cliente();
        String sql="select * from cliente where Dni="+dni;
        try {
            con = cn.Conexion();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        }catch (Exception e) {
            
        }
        return c;
    }
           
    //Operaciones del crud

    public List listar() {
        String sql = "select * from CLIENTE";
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstado(rs.getString(5));
                lista.add(cl);
            }
            System.out.println("Consulta de manera exitosa para cliente");
        } catch (Exception e) {

        }
        return lista;
    }

    public void agregar(Cliente cl) {
        String sql = "insert into cliente(Dni, Nombres, Direccion, Estado)values(?,?,?,?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEstado());
            ps.executeUpdate();
            System.out.println("Insertó de manera exitosa para cliente");
        } catch (Exception e) {

        }
        //return r;
    }

    public Cliente listarId(int id) {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstado(rs.getString(5));
            }
            System.out.println("Lista el cliente id de manera exitosa");
        } catch (Exception e) {

        }
        return cl;

    }

    public void actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        System.out.println("Preparando sentencia update");
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEstado());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();
            System.out.println("Actualiza de manera exitosa el cliente");
        } catch (Exception e) {

        }
        //return r;
    }

    public void delete(int id) {
        String sql = "delete from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Elimina de manera exitosa el cliente");
        } catch (Exception e) {

        }
    }
}
