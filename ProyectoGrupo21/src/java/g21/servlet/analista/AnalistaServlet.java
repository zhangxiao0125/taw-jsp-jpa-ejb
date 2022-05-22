/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.servlet.analista;

import g21.dao.EstudiosFacade;
import g21.dao.UsuarioFacade;
import g21.dto.EstudiosDTO;
import g21.dto.marketing.UsuarioDTO;
import g21.entity.Estudios;
import g21.entity.Usuario;
import g21.service.EstudiosService;
import g21.service.marketing.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernando Jesús Ruano Linares
 */
@WebServlet(name = "AnalistaServlet", urlPatterns = {"/AnalistaServlet"})
public class AnalistaServlet extends HttpServlet {

    @EJB UsuarioFacade uf;
    @EJB EstudiosService es;
    @EJB UsuarioService usuarioService;
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
        response.setContentType("text/html;charset=UTF-8");
        
        Integer userid = Integer.parseInt(request.getParameter("analistaid"));
        Usuario usuario = this.uf.find(userid);
        UsuarioDTO usuariodto = this.usuarioService.findDTO(userid.toString());
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuariodto);
        
        List<EstudiosDTO> estudios = this.es.FindByUser(usuario);
        request.setAttribute("estudios", estudios);
        
        request.getRequestDispatcher("/WEB-INF/analista/analista.jsp").forward(request, response);
        
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
