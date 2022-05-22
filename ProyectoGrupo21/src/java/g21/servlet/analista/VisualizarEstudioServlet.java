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
@WebServlet(name = "VisualizarEstudioServlet", urlPatterns = {"/VisualizarEstudioServlet"})
public class VisualizarEstudioServlet extends HttpServlet {

    @EJB
    EstudiosService es;

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

        HttpSession session = request.getSession();
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        Integer estudioid = Integer.parseInt(request.getParameter("estudioid"));
        EstudiosDTO estudio = this.es.buscarEstudio(estudioid);
        List<Object[]> datos = this.es.QueryOnDemand(estudio.getQuery());

        //inicializacion del titulo
        if (!datos.isEmpty() && (estudio.getTitulos() == null || estudio.getTitulos().equals(""))) {
            String strt = "";

            for (int i = 0; i < datos.get(0).length; i++) {
                strt = strt + "_;";
            }

            es.modificarEstudio(estudioid, estudio.getAnalistaId(), estudio.getNombre(), estudio.getQuery(), strt);
        }

        estudio = this.es.buscarEstudio(estudioid);
        request.setAttribute("estudio", estudio);
        request.setAttribute("datos", datos);

        request.getRequestDispatcher("/WEB-INF/analista/nuevoestudio2.jsp").forward(request, response);
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
