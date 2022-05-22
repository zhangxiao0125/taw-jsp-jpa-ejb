/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.servlet.marketing;

import g21.dao.ListaFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Usuario;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import g21.entity.Lista;

/**
 *
 * @author zhang
 */
@WebServlet(name = "CompradoresDeListaServlet", urlPatterns = {"/CompradoresDeListaServlet"})
public class CompradoresDeListaServlet extends HttpServlet {

    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ListaFacade listaFacade;

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
        String id = request.getParameter("id");
//        
//        request.setAttribute("lista", usuarioFacade.buscarLista(Integer.parseInt(id)));
//        request.setAttribute("compradores", usuarioFacade.Comprador());
//        
//        request.getRequestDispatcher("compradores.jsp").forward(request, response);

        //List<Usuario> compradores = this.usuarioFacade.filtroComprador(Integer.parseInt(id));
        Lista lista = this.listaFacade.find(Integer.parseInt(id));

        List<Usuario> compradoresDisponibles = this.usuarioFacade.getCompradoresDisponibles(Integer.parseInt(id));
        request.setAttribute("lista", lista);
        request.setAttribute("compradoresDisponibles", compradoresDisponibles);
        
        request.getRequestDispatcher("/WEB-INF/marketing/compradoresDeLista.jsp").forward(request, response);
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
