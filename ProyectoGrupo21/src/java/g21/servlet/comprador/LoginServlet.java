/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.comprador;

import g21.dao.UsuarioFacade;
import g21.entity.Usuario;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cecil
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
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
        
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            
            Usuario userbyemail = this.uf.findbyemail(email);
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", userbyemail);
            if(userbyemail == null)
            {
                String message = "Usuario no existe. Por favor registrate";
                
                response.sendRedirect(request.getContextPath() + "/FirstServlet?message="+message);
            }
            else
            {
                String message = null;
                request.setAttribute("message", message);
                request.setAttribute("usuario", userbyemail);
                
                //ver el rol de este usuario
                if(userbyemail.getRolId().getRolId().equals(1)) {   //comprador
                    request.getRequestDispatcher("/WEB-INF/comprador/usuario.jsp").forward(request, response);
                } else if (userbyemail.getRolId().getRolId().equals(2)) {   //analista
                    response.sendRedirect(request.getContextPath() + "/AnalistaServlet?analistaid="+userbyemail.getUserId());
                }  else if (userbyemail.getRolId().getRolId().equals(5)) {  //Marketing
                    response.sendRedirect(request.getContextPath() + "/ListasCompradorServlet");
                }
                
                
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
