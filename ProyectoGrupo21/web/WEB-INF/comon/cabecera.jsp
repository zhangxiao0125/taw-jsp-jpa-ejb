<%-- 
    Document   : cabecera
    Created on : May 22, 2022, 8:53:39 PM
    Author     : Xiao
--%>

<%@page import="g21.dto.marketing.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<head>
    <style>
        #cabecera  {
            border-collapse: collapse;
            width: 100%;
        
        }
        #cabecera th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
            border: 1px solid #ddd;
            padding: 8px;
        }
    </style>
</head>
<table width="80%" id="cabecera">
    <tr width="80%">
        <th>Bienvenido, <%= usuario.getNombre()%></td>
        <th>Session ID: <%= session.getId()%></td>
        <th><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>