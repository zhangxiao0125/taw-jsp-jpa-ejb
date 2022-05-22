<%-- 
    Document   : productocomprado
    Created on : May 14, 2022, 10:09:09 PM
    Author     : cecil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="g21.entity.Producto"%>
<%@page import="g21.entity.Usuario"%>
<%@page import="g21.entity.Puja"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>producto comprado</title>
        <style>
            table, th, td {
                border: 1px solid black;
                text-align: center;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario1");
            List<Producto> prodcompr = usuario.getProductoList1();
        %>
        <h1>Producto Comprado</h1>


        <%
            if (prodcompr.isEmpty()) {
        %>
        <p>No Producto Comprado</p>
        <%
        } else {
        %>
        <form action="BusquedaServlet?userid=<%=usuario.getUserId()%>&prod=compr" method="post">
            <input type="text" name="search" value=""/>
            <input type="submit" name="Enviar"/>
        </form>
        </br>
        <%
            for (Producto p : prodcompr) {
                Usuario vendedor = p.getVendedorId();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                Date compuja = p.getComienzoPuja();
                Date finpuja = p.getFinalPuja();
                String comienzopuja = DateFor.format(compuja);
                String finalpuja = DateFor.format(finpuja);


        %>

        <table>
            <tr>

                <th>Titulo:</th>
                <th><%= p.getTitulo()%></th>

            </tr>
            <tr>
                <th>Descripcion:</th>
                <td><%= p.getDescripcion()%></td>
            </tr>
            <tr>
                <th>Vendedor:</th>
                <td><%= vendedor.getNombre()%> <%= vendedor.getApellido()%></td>
            </tr>
            <tr>
                <td colspan="2" align = "center"><img src="<%= p.getUrlFoto()%>" width="150" height="150"></td>
            </tr>
            <tr>
                <th>Precio</th>
                <td><%= p.getPrecio()%>$ </td>

            </tr>
            <tr>
                <th>Comienzo Puja</th>
                <td><%= comienzopuja%> </td>

            </tr>
            <tr>
                <th>Final Puja</th>
                <td><%= finalpuja%> </td>

            </tr>


        </table>
        </br>
        <%
                }
            }
        %>

    </body>
</html>
