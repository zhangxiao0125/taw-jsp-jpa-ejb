<%-- 
    Author     : aannu
--%>

<%@page import="g21.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body>
        <h1>Categorias</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>    
            </tr>
        <%
            List<Categoria> categorias = (List)request.getAttribute("categorias");
            for (Categoria categoria: categorias) {
        %>    
        <tr>
            <td><%= categoria.getCatId() %></td>
            <td><%= categoria.getNombre() %></td>
             <td><a href="CategoriaBorrarServlet?id=<%= categoria.getCatId() %>">Borrar</a></td>
             <td><a href="CategoriaNuevoEditarServlet?id=<%= categoria.getCatId() %>">Editar</a></td>
        </tr>
        
        <%
            }
        %>
        </table>
        <a href="CategoriaNuevoEditarServlet">Crear nueva categoria ... </a>
    </body>
</html>
