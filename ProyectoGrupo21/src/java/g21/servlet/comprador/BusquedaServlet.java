/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package g21.servlet.comprador;

import g21.dao.ProductoFacade;
import g21.dao.UsuarioFacade;
import g21.entity.Producto;
import g21.entity.Usuario;
import javax.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cecil
 */
@WebServlet(name = "BusquedaServlet", urlPatterns = {"/BusquedaServlet"})
public class BusquedaServlet extends HttpServlet {
    @EJB UsuarioFacade uf;
    @EJB ProductoFacade pf;
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
        String search = request.getParameter("search");
        String userid = request.getParameter("userid");
        String prod = request.getParameter("prod");
        
        int id = Integer.parseInt(userid);
        Usuario usuario = this.uf.findbyuserid(id);
        request.setAttribute("usuario1", usuario);
        
        
        List<Producto> res_search = (List<Producto>) this.pf.findsearch(search);
        
        if(prod.equals("tot"))
        {
            request.setAttribute("ressearch", res_search);
            request.getRequestDispatcher("/WEB-INF/comprador/search.jsp").forward(request, response);
        }    
        if(prod.equals("fav"))
        {
            List<Producto> pd = usuario.getProductoList();
            
            List<Producto> res_search2 = new ArrayList<>();
            
            
            for(Producto p: res_search)
            {
                if(pd.contains(p))
                {
                    res_search2.add(p);

                }
            }
            
            
            request.setAttribute("ressearch", res_search2);

            request.getRequestDispatcher("/WEB-INF/comprador/search.jsp").forward(request, response);

        }

        if(prod.equals("compr"))
        {
            List<Producto> pd = usuario.getProductoList1();
            
            List<Producto> res_search2 = new ArrayList<>();
            
            
            for(Producto p: res_search)
            {
                if(pd.contains(p))
                {
                    res_search2.add(p);

                }
            }
            
            
            request.setAttribute("ressearch", res_search2);

            request.getRequestDispatcher("/WEB-INF/comprador/search.jsp").forward(request, response);

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
