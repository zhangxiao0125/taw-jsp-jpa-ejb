<%-- 
    Author     : aannu
--%>

<%@page import="g21.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%

       Categoria categoria = (Categoria)request.getAttribute("categoria");
    %>  
    <body>
        <h1>Datos del categoria</h1>
        <form method="POST" action="CategoriaGuardarServlet">
            <input type="hidden" name="id" value="<%= categoria==null? "": categoria.getCatId() %>" />
            Nombre: <input type="text" size="50" name="nombre" value="<%= categoria==null? "": categoria.getNombre() %>" /> <br/>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
