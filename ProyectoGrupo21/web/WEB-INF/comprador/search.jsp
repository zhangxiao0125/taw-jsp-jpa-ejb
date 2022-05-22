<%-- 
    Document   : search
    Created on : May 14, 2022, 10:53:52 PM
    Author     : cecil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="g21.entity.Producto"%>
<%@page import="g21.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table, th, td {
                border: 1px solid black;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        List<Producto> producto = (List<Producto>) request.getAttribute("ressearch");
        Usuario usuario = (Usuario) request.getAttribute("usuario1");
    %>
    <body>
        <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

        <h1>Results</h1>
        <%
            if (producto.isEmpty()) {
        %>
        <p>No results</p>
        <%
        } else {
            for (Producto p : producto) {
                Usuario vendedor = p.getVendedorId();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                Date compuja = p.getComienzoPuja();
                Date finpuja = p.getFinalPuja();
                String comienzopuja = DateFor.format(compuja);
                String finalpuja = DateFor.format(finpuja);


        %>
        <form action = "AddProductoFavorito" method="post">
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
                    <td><%= p.getPrecio()%> </td>

                </tr>
                <tr>
                    <th>Comienzo Puja</th>
                    <td><%= comienzopuja%> </td>

                </tr>
                <tr>
                    <th>Final Puja</th>
                    <td><%= finalpuja%> </td>

                </tr>
                <tr>
                    <th colspan="2"><a href="AddProductoFavorito?userid=<%= usuario.getUserId()%>&prodid=<%= p.getProductId()%>">Producto Favorito</a></th>
                </tr>

            </table>
            </br>
            <%
                    }
                }
            %>
        </form>
    </body>
</html>
