<%-- 
    Author     : Fernando Jesús Ruano Linares
--%>

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
        String error = (String)request.getAttribute("error");
        if (error == null) error = "";
        String stqr = (String)request.getAttribute("query");
        if (stqr == null) stqr = "";
    %>
    <body>
        <h1>Nuevo estudio</h1>
        <br>
        
        
        <form method="POST" action="CheckStudioServlet">
            Query: <input name="query" type="text" value="<%= stqr %>">
            <input type="hidden" name="analistaid" value="<%= usuario.getUserId() %>" />
            <input type="submit" value="Enviar">
        </form>
        
        <%= error %>
        <br>
        <a href="AnalistaServlet?analistaid=<%= usuario.getUserId() %>">Volver al inicio</a>
    </body>
</html>
