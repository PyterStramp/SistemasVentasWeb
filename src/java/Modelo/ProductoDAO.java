
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Operaciones del crud
   
    public List listar() {
        String sql = "select * from producto";
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pr = new Producto();
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
                lista.add(pr);
            }
            System.out.println("Consulta de manera exitosa para producto");
        } catch (Exception e) {

        }
        return lista;
    }

    public void agregar(Producto pr) {
        String sql = "insert into producto(Nombres, Precio, Stock, Estado)values(?,?,?,?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNom());
            ps.setString(2, Double.toString(pr.getPrecio()));
            ps.setString(3, Integer.toString(pr.getStock()));
            ps.setString(4, pr.getEstado());
            ps.executeUpdate();
            System.out.println("Insertó de manera exitosa para producto");
        } catch (Exception e) {

        }
        //return r;
    }

    public Producto listarId(int id) {
        Producto pr = new Producto();
        String sql = "select * from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
            System.out.println("Lista el producto id de manera exitosa");
        } catch (Exception e) {

        }
        return pr;

    }

    public void actualizar(Producto pr) {
        String sql = "update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        System.out.println("Preparando sentencia update");
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNom());
            ps.setString(2, Double.toString(pr.getPrecio()));
            ps.setString(3, Integer.toString(pr.getStock()));
            ps.setString(4, pr.getEstado());
            ps.setInt(5, pr.getId());
            ps.executeUpdate();
            System.out.println("Actualiza de manera exitosa el producto");
        } catch (Exception e) {

        }
        //return r;
    }

    public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Elimina de manera exitosa el producto");
        } catch (Exception e) {

        }
    }    
    
    public void actualizarstock (int id, int stock) {
        String sql = "update producto set Stock=? where IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
}
