/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.comprador;

import g21.dao.ProductoFacade;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cecil
 */
@WebServlet(name = "AsinchrServlet", urlPatterns = {"/AsinchrServlet"})
public class AsinchrServlet extends HttpServlet {
    @EJB ProductoFacade pf;
    @EJB UsuarioFacade uf;
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
        
        
        
        String message = null;
        String error = null;
        
        LocalDate date = LocalDate.now();
        
        String userid =  request.getParameter("id");
        int uid = Integer.parseInt(userid);
        Usuario usuario = this.uf.findbyuserid(uid);
        
        List<Producto> producto = this.pf.findAll();
        for(Producto p: producto)
        {
            
            Date finpuja = p.getFinalPuja();   
            LocalDate fpuja = finpuja.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //if(date.isAfter(fpuja))
            //{
                List<Puja> pujalist = p.getPujaList();  
                
                int len = pujalist.size()-1;
                out.println(len);
                if(len == -1)
                {
                    error = "No comprador";
                    request.setAttribute("error", error);
                }
                else
                {
                    Puja lastpuja = pujalist.get(len);
                    Usuario comprador = lastpuja.getCompradorId();
                    List<Producto> prodcompr = comprador.getProductoList1();
                    if(!prodcompr.contains(p))
                    {
                        if(comprador.equals(usuario))
                        {
                            message = "Nuovo Producto Comprado";
                              
                        }
                        prodcompr.add(p);
                        comprador.setProductoList1(prodcompr);
                        this.uf.edit(comprador);
                    }
               }
                
                
                
                
            //}
        }
        request.setAttribute("message", message);  
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
