<%-- 
    Document   : usuario
    Created on : May 14, 2022, 4:13:44 PM
    Author     : cecil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="g21.entity.Usuario"%>
<%@page import="g21.entity.Producto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% 
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            List<Producto> prodfav = usuario.getProductoList();
            
        %>
    </head>
    <body>
        <h1>Welcome, <%= usuario.getNombre() %> <%= usuario.getApellido() %></h1>
        <p>
            <a href="ListarProductoServlet?id=<%=usuario.getUserId()%>">Lista Producto</a></br>
            </br>
            <a href="ProductoCompradoServlet?id=<%=usuario.getUserId()%>">Productos Comprados</a></br>
            </br>
            <a href="ProductoFavoritosServlet?id=<%=usuario.getUserId()%>">Productos Favoritos</a></br>
        </p>
    </body>
</html>
