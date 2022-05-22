/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.comprador;

import g21.dao.ProductoFacade;
import g21.dao.PujaFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Producto;
import g21.entity.Puja;
import g21.entity.Usuario;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cecil
 */
@WebServlet(name = "PujaServlet", urlPatterns = {"/PujaServlet"})
public class PujaServlet extends HttpServlet {
    @EJB UsuarioFacade uf;
    @EJB ProductoFacade pf;
    @EJB PujaFacade puf;
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
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");
        String prodid = request.getParameter("prodid");
        String puja = request.getParameter("puja");
        out.print(puja);
        int uid = Integer.parseInt(userid);
        int proid = Integer.parseInt(prodid); 
        BigDecimal num_puja = new BigDecimal(puja);
        Usuario usuario = this.uf.findbyuserid(uid);
        Producto producto = this.pf.findbyid(proid);
        producto.setPrecio(num_puja);
        this.pf.edit(producto);
        LocalDate Fecha = LocalDate.now();
        Date date = Date.from(Fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Puja newpuja = new Puja();
        newpuja.setCantidad(num_puja);
        newpuja.setFecha(date);
        newpuja.setProductId(producto);
        newpuja.setCompradorId(usuario);
        this.puf.create(newpuja);
        
        List<Puja> pujaproducto = producto.getPujaList();
        pujaproducto.add(newpuja);
        producto.setPujaList(pujaproducto);
        this.pf.edit(producto);
        
        
        
        
        response.sendRedirect(request.getContextPath() + "/ListarProductoServlet?id="+uid);
        
        
        
        
        
        
        
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
