<%-- 
    Document   : usuarios
    Created on : Apr 24, 2022, 7:32:47 PM
    Author     : zhang
--%>

<%@page import="g21.entity.Lista"%>
<%@page import="java.util.List"%>
<%@page import="g21.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Listado de compradores</title>
    </head>
    <body>
        <h1> <a href="ListasCompradorServlet">Volver a listas </a></h1>
        <%
            List<Usuario> compradoresDisponibles = (List) request.getAttribute("compradoresDisponibles");
            Lista lista = (Lista) request.getAttribute("lista");
        %>

        <!-- Compradores que no estan en esta lista -->        
        <h1>Compradores de la lista <%= lista.getNombre()%></h1>
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
                <th></th>
            </tr>
            <%

                for (Usuario comprador : lista.getUsuarioList()) {
            %>
            <tr>
                <td><%= comprador.getNombre()%></td>
                <td><%= comprador.getApellido()%></td>
                <td><%= comprador.getEmail()%></td>
                <td><%= comprador.getDireccion()%></td>
                <td><%= comprador.getRolId().getNombre()%></td>
                <td><a href="BorrarCompradorDesdeListaServlet?listaId=<%= lista.getListaId()%>&compradorId=<%= comprador.getUserId()%>">Borrar</a></td>
                <td><a href="VerMensajesServlet?compradorId=<%= comprador.getUserId()%>">Ver Mensajes</a></td>
            </tr>

            <%
                }
            %>
        </table>





        <!-- Compradores que no estan en esta lista -->        
        <h1>Compradores Disponibles</h1>
        <h5>Descripcion: En esta tabla solo se muestran los compradores que no estan en la tabla de arriba</h5>
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
                for (Usuario comprador : compradoresDisponibles) {
            %>
            <tr>
                <td><%= comprador.getNombre()%></td>
                <td><%= comprador.getApellido()%></td>
                <td><%= comprador.getEmail()%></td>
                <td><%= comprador.getDireccion()%></td>
                <td><%= comprador.getRolId().getNombre()%></td>
                <td><a href="AnadirCompradorAListaServlet?listaId=<%= lista.getListaId()%>&compradorId=<%= comprador.getUserId()%>">Añadir</a></td>
            </tr>

            <%
                }
            %>
        </table>
        <br>
        <br>
        <br>




        <!-- Notificacion -->
        Notificacion:<br/>
        <form method="POST" action="NotificarServlet">
            <input type="hidden" name="idLista" value="<%= lista == null ? "" : lista.getListaId()%>" />
            <textarea name="notificacion" rows="10" cols="60">
            </textarea>
            <br>
            <input type="submit" value="Enviar Mensaje" />

        </form>

    </body>
</html>
