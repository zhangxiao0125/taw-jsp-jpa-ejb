<%-- 
    Document   : lista
    Created on : 15-may-2022, 19:22:02
    Author     : zhang
--%>

<%@page import="g21.entity.Lista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%

        Lista lista = (Lista) request.getAttribute("lista");
    %>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle de la lista <%= lista == null ? "" : lista.getNombre()%> </title>
    </head>
    
    <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

    <h1> <a href="ListasCompradorServlet">Volver a listas </a></h1>

    <body>
        <h1>Detalle de la lista <%= lista == null ? "" : lista.getNombre()%></h1>
        <form method="POST" action="ListaCrearEditarSevlet">
            <input type="hidden" name="id" value="<%= lista == null ? "" : lista.getListaId()%>" />
            Nombre de lista: <input type="text" size="30" name="nombre" value="<%= lista == null ? "" : lista.getNombre()%>" /><br/>

            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
