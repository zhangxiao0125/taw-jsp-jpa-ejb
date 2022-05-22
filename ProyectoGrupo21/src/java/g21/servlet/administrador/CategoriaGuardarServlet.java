/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.administrador;

import g21.dao.CategoriaFacade;
import g21.entity.Categoria;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aannu
 */
@WebServlet(name = "CategoriaGuardarServlet", urlPatterns = {"/CategoriaGuardarServlet"})
public class CategoriaGuardarServlet extends HttpServlet {
    
        @EJB CategoriaFacade cf;

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
        
        String strId, str;
        Categoria categoria;
        
        strId = request.getParameter("id");
        
        if (strId == null || strId.isEmpty()) {    // Crear nueva categoria
            categoria = new Categoria();
        } else {                               // Editar categoria
            categoria = this.cf.find(Integer.parseInt(strId));
        }  
        
        
        str = request.getParameter("nombre");
        categoria.setNombre(str);
        
        
        if (strId == null || strId.isEmpty()) {    // Crear nueva categoria
            cf.create(categoria);
        } else {                                   // Editar catgeoria
        }        
                
       response.sendRedirect(request.getContextPath() + "/CategoriaServlet");
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
