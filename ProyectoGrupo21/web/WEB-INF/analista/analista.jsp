<%-- 
    Author     : Fernando Jesús Ruano Linares
--%>

<%@page import="g21.dto.EstudiosDTO"%>
<%@page import="java.util.List"%>
<%@page import="g21.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        List<EstudiosDTO> estudios = (List)request.getAttribute("estudios");
    %>
    
    <body>
        <h1>Hola analista</h1>
        
        <table BORDER="1">
            <tr>
                <th>NOMBRE DEL ESTUDIO</th>
                <th>QUERY</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            
        <%
          for (EstudiosDTO e : estudios) { %>
          <tr>
              <td> <%= e.getNombre() %></td>
              <td> <%= e.getQuery() %></td>
              <td> <a href="VisualizarEstudioServlet?estudioid=<%= e.getEstudioId() %>">Visualizar Estudio</a> </td>
              <td> <a href="NuevoEstudioServlet?query=<%= e.getQuery() %>">Clonar Estudio</a></td>
              <td> <a href="BorrarEstudioServlet?estudioid=<%= e.getEstudioId() %>">Borrar Estudio</a></td>
          </tr>
          
          
        <%  }  
        %>
            
          
            
        </table>
        
        <a href="NuevoEstudioServlet?analistaid=<%= usuario.getUserId() %>">Crear nuevo estudio ... </a>  

        
    </body>
</html>
