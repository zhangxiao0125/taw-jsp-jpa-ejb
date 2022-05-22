/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g21.servlet.marketing;

import g21.dao.ListaFacade;
import g21.entity.Lista;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhang
 */
@WebServlet(name = "ListaCrearEditarSevlet", urlPatterns = {"/ListaCrearEditarSevlet"})
public class ListaCrearEditarSevlet extends HttpServlet {

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

        // para crear uno nuevo
        String nombre = null;
        String id = null;

        nombre = request.getParameter("nombre");
        id = request.getParameter("id");

        Lista lista = new Lista();
        if (nombre != null && nombre.length() > 0) {
            lista.setNombre(nombre);
        }

        try {
            if (id != null) {
                lista.setListaId(Integer.parseInt(id));
            }
        } catch (Exception e) {

        } finally {

        }

        // si el id es invalido, creamos uno nuevo.
        if (lista.getListaId() == null) {
            this.listaFacade.create(lista);
        } else {
            // si el id es valido, editamos la antigua
            this.listaFacade.edit(lista);
        }
        response.sendRedirect(request.getContextPath() + "/ListasCompradorServlet");

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