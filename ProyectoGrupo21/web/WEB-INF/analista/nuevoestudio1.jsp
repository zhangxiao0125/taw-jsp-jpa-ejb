<%-- 
    Author     : Fernando Jesús Ruano Linares
--%>

<%@page import="g21.dto.marketing.UsuarioDTO"%>
<%@page import="g21.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        String error = (String) request.getAttribute("error");
        if (error == null) {
            error = "";
        }
        String stqr = (String) request.getAttribute("query");
        if (stqr == null) {
            stqr = "";
        }
    %>
    <body>
        <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

        <h1>Nuevo estudio</h1>
        <br>


        <form method="POST" action="CheckStudioServlet">
            Query: <input name="query" type="text" value="<%= stqr%>">
            <input type="hidden" name="analistaid" value="<%= usuario.getId()%>" />
            <input type="submit" value="Enviar">
        </form>

        <%= error%>
        <br>
        <a href="AnalistaServlet?analistaid=<%= usuario.getId()%>">Volver al inicio</a>
    </body>
</html>
