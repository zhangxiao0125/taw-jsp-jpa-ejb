/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.servlet.marketing;

import g21.dao.NotificacionFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Lista;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import g21.entity.Notificacion;
import g21.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author zhang
 */
@WebServlet(name = "VerMensajesServlet", urlPatterns = {"/VerMensajesServlet"})
public class VerMensajesServlet extends HttpServlet {

    @EJB
    NotificacionFacade nf;
    @EJB
    UsuarioFacade usuarioFacade;

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
        String id;
        id = request.getParameter("compradorId");
        List<Notificacion> notificaciones = new ArrayList<>();
        Usuario comprador=new Usuario();
        if (id != null) {
            notificaciones = this.nf.getTodasNotificaciones(Integer.parseInt(id));
            comprador = this.usuarioFacade.find(Integer.parseInt(id));
        }
        request.setAttribute("notificaciones", notificaciones);
        request.setAttribute("comprador", comprador);
        request.getRequestDispatcher("/WEB-INF/marketing/mensajes.jsp").forward(request, response);

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
