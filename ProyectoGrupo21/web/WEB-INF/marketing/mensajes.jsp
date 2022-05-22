<%-- 
    Document   : mensajes
    Created on : 15-may-2022, 22:19:32
    Author     : zhang
--%>

<%@page import="g21.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="g21.entity.Notificacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head
    <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

    <h1> <a href="ListasCompradorServlet">Volver a listas </a></h1>

    <%
        List<Notificacion> notificaciones = (List) request.getAttribute("notificaciones");
        Usuario usuario = (Usuario) request.getAttribute("comprador");
    %>
    <body>
        <h1> Bandea de mensajes del comprador <%= usuario.getNombre()%> <%= usuario.getApellido()%> </h1>

        <table border="1">
            <tr>
                <th>Mensaje</th>
                <th>Mensajero</th>
                <th>Fecha</th>
            </tr>
            <%
                for (Notificacion notificacion : notificaciones) {
            %>
            <tr>
                <td><%= notificacion.getMensaje()%></td>
                <td><%= notificacion.getMensajero().getNombre()%> <%= notificacion.getMensajero().getApellido()%> </td>
                <td><%= notificacion.getFecha()%></td>
            </tr>

            <%
                }
            %>
        </table>
    </body>
</html>
