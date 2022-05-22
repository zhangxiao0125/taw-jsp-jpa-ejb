<%-- 
    Document   : listasComprador
    Created on : 15-may-2022, 18:13:18
    Author     : zhang
--%>

<%@page import="g21.entity.Usuario"%>
<%@page import="g21.entity.Lista"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listas de compradores</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

        <h1>Listas de compradores</h1>
        <%
            List<Lista> listasComprador = (List) request.getAttribute("listasComprador");
            List<Usuario> compradores = (List) request.getAttribute("compradores");
            if (listasComprador == null || listasComprador.isEmpty()) {
        %>
        No hay niuguna lista
        <br/>
        <%
        } else {
        %>
        <form method="post" action="CompradorServlet">
            Nombre: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
        </form>
        <br/>
        <table border="1">
            <tr>
                <th>NOMBRE DE LISTA</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                for (Lista lista : listasComprador) {
            %>

            <tr>
                <td><%= lista.getNombre()%></td>
                <td><a href="CompradoresDeListaServlet?id=<%= lista.getListaId()%>">Ver lista</a></td>
                <td><a href="ListaNuevoEditarForm?id=<%= lista.getListaId()%>">Editar</a></td>
                <td><a href="ListaBorrarServlet?id=<%= lista.getListaId()%>">Borrar</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <a href="ListaNuevoEditarForm">Crear nueva lista de compradores ... </a>




        <!-- Compradores-->        
        <h1>Todos los compradores</h1>
        <form method="post" action="CompradoresDeListaServlet">
            <input type="text" name="filtroRol" value="" />
            <input type="submit" value="Filtrar" />
        </form>

        <table border="1">
            <tr>
                <th>NOMBRE</th>
                <th>APELLIDO</th>
                <th>EMAIL</th>
                <th>DIRECCION</th>
                <th>ROL</th>
                <th></th>

            </tr>
            <%
                for (Usuario comprador : compradores) {
            %>
            <tr>
                <td><%= comprador.getNombre()%></td>
                <td><%= comprador.getApellido()%></td>
                <td><%= comprador.getEmail()%></td>
                <td><%= comprador.getDireccion()%></td>
                <td><%= comprador.getRolId().getNombre()%></td>
                <td><a href="VerMensajesServlet?compradorId=<%= comprador.getUserId()%>">Ver Mensajes</a></td>
            </tr>

            <%
                }
            %>
        </table>
    </body>
</html>
