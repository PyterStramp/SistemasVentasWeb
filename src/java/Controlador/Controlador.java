/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.ClienteDTO;
import Modelo.Empleado;
import Modelo.EmpleadoDTO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.ProductoDTO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

/**
 *
 * @author Hogar
 */
public class Controlador extends HttpServlet {
    int ide;
    EmpleadoDTO edto = new EmpleadoDTO();
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    ClienteDTO cdto = new ClienteDTO();
    Producto pr = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    ProductoDTO pdto = new ProductoDTO();
    //venta
    Venta v = new Venta();
    List<Venta>lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    //numserie
    String numeroserie;
    VentaDAO vdao = new VentaDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu.equals("Empleado")) { //empleado para empleado.jsp
            switch (accion) {
                case "Listar":
                    List listaEmpleado = edto.listarEmpleado();
                    request.setAttribute("empleados", listaEmpleado);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    edto.agregarEmpleado(dni, nom, tel, est, user);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edto.editarEmpleado(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    edto.actualizarEmpleado(dni1, nom1, tel1, est1, user1, ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edto.eliminarEmpleado(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        //separar para cliente
        if (menu.equals("Clientes")) { //para cliente.jsp
            switch (accion) {
                case "Listar":
                    List lista = cdto.listarCliente();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":                  
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDir");
                    String est = request.getParameter("txtEstado");
                    cdto.agregarCliente(dni, nom, dir, est);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;   
                case "Editar":               
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente c = cdto.editarCliente(ide);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;                   
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDir");
                    String est1 = request.getParameter("txtEstado");
                    cdto.actualizarCliente(dni1, nom1, dir1, est1, ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);      
                    break;           
                case "Delete":    
                    ide = Integer.parseInt(request.getParameter("id"));
                    cdto.eliminarCliente(ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response); 
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        //separar para producto
        if (menu.equals("Producto")) { //producto.jsp
            switch (accion) {
                case "Listar":
                    List lista = pdto.listarProducto();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    String precio = request.getParameter("doublePrecio");
                    String stock = request.getParameter("intStock");
                    String est = request.getParameter("txtEstado");
                    pdto.agregarProductoe(nom, precio, stock, est);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;                   
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Producto p = pdto.editarProducto(ide);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;               
                case "Actualizar":       
                    String nom1 = request.getParameter("txtNombres");
                    String precio1 = request.getParameter("doublePrecio");
                    String stock1 = request.getParameter("intStock");
                    String est1 = request.getParameter("txtEstado");
                    pdto.actualizarProducto(nom1, precio1, stock1, est1, ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);     
                    break;                   
                case "Delete":                
                    ide = Integer.parseInt(request.getParameter("id"));
                    pdto.eliminarProducto(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);               
                    break;
                default:
                    throw new AssertionError();
            }            
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        //separar para venta
        if (menu.equals("NuevaVenta")) {
            v=new Venta();
            switch(accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    cl.setDni(dni);
                    cl=cdao.buscar(dni);
                    request.setAttribute("c",cl);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    pr = pdao.listarId(id);
                    request.setAttribute("c",cl);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "AgregarProducto":
                    request.setAttribute("c",cl);
                    totalPagar = 0.0;
                    item = item+1;
                    cod = pr.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant= Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio*cant;
                    
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i=0; i<lista.size(); i++) {
                        totalPagar=totalPagar +lista.get(i).getSubtotal();
                    }
                    
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    break;
                /*case "EditarProducto":
                    System.out.println("Editar para producto en ventas");                   
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente c = cdao.listarId(ide);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    
                    break;
                case "ActualizarProducto":
                    
                    break;    
                */    
                case "EliminarProducto":
                    ide = Integer.parseInt(request.getParameter("id"));
                    
                    request.getRequestDispatcher("Controlador?menu=NuevaVenta").forward(request, response);                    
                    break;

                case "GenerarVenta":
                    //stock
                    
                    for (int i =0; i<lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr=aO.listarId(idproducto);
                        int stockactual = pr.getStock() - cantidad;
                        aO.actualizarstock(idproducto, stockactual);
                    }
                    
                    //Guardar venta
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(8);
                    v.setNumserie(numeroserie);
                    LocalDate fechaDate = java.time.LocalDate.now();
                    v.setFecha(fechaDate.toString());
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar detalle venta
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i=0; i<lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    request.getRequestDispatcher("Controlador?menu=NuevaVenta&accion=default").forward(request, response);
                    break;
                default:
                    lista.clear();
                    item=0;
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie==null) {
                        numeroserie="00000001";
                        request.setAttribute("nserie", numeroserie);
                    }
                    else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        if (menu.equals("Principal")) { 
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
