/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.comprador;

import g21.dao.CategoriaFacade;
import g21.dao.RolFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Categoria;
import g21.entity.Rol;
import g21.entity.Usuario;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cecil
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    @EJB UsuarioFacade uf;
    @EJB CategoriaFacade cf;
    @EJB RolFacade rf;
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
            
       
            Usuario user = new Usuario();
            
            String comprador = "Comprador";
            Rol rolid = this.rf.findbynombre(comprador);
            user.setRolId(rolid);
            
            String nombre = request.getParameter("nombre");
            user.setNombre(nombre);
            
            String apellido = request.getParameter("apellido");
            user.setApellido(apellido);
            
            String email = request.getParameter("email");
            user.setEmail(email);
            
            String domicilio = request.getParameter("domicilio");
            user.setDireccion(domicilio);
            
            String ciudad = request.getParameter("ciudad");
            user.setCiudad(ciudad);
            
            String age = request.getParameter("edad");
            int edad = Integer.parseInt(age);
            user.setEdad(edad);
            
            String sex = request.getParameter("sexo");
            char sexo = sex.charAt(0);
            user.setSexo(sexo);
            
            
           
            
            List<Categoria> catpref = new ArrayList<>();
            String cat[] = request.getParameterValues("cat");
            for(String c: cat)
            {
                Categoria cate = this.cf.findbynombre(c);
                catpref.add(cate);
            }
            user.setCategoriaList(catpref); 
            this.uf.create(user);
            
            request.setAttribute("usuario", user);
            request.getRequestDispatcher("/WEB-INF/comprador/usuario.jsp").forward(request, response);
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
