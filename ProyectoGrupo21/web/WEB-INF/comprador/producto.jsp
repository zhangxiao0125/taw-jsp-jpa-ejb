<%-- 
    Document   : producto
    Created on : May 14, 2022, 5:21:27 PM
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
        <style>
            table, th, td {
                border: 1px solid black;
                text-align: center;
                border-collapse: collapse;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Producto</title>

        <%
            List<Producto> producto = (List<Producto>) request.getAttribute("producto");
            Usuario usuario = (Usuario) request.getAttribute("usuario1");
            String message = (String) request.getAttribute("message");
            request.setAttribute("producto", producto);

        %>
    </head>
    <body>

        <jsp:include page="/WEB-INF/comon/cabecera.jsp" />           

        <h1>Productos <a href="BackServlet?id=<%= usuario.getUserId()%>">Back</a>

            <form action = "AsinchrServlet?id=<%= usuario.getUserId()%>" method="post">
                <input type="submit" value="Refresh"/>
            </form>
            <%
                if (message != null) {
            %>
            <%= message%>   
            <%
                }
            %>
        </h1>

        <form action="BusquedaServlet?userid=<%=usuario.getUserId()%>&prod=tot" method="post">
            <input type="text" name="search" value=""/>
            <input type="submit" name="Enviar"/>
        </form>
        </br>


        <%
            for (Producto p : producto) {
                List<Puja> pujalist = p.getPujaList();
                LocalDate today = LocalDate.now();
                Usuario vendedor = p.getVendedorId();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                Date compuja = p.getComienzoPuja();
                Date finpuja = p.getFinalPuja();
                String comienzopuja = DateFor.format(compuja);
                String finalpuja = DateFor.format(finpuja);
                LocalDate fpuja = finpuja.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        %>
        <form action = "AddProductoFavorito" method="post">
            <table style="width:50%">
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
                    <td colspan="2"><img src="<%= p.getUrlFoto()%>" width="150" height="150"></td>
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
                <% if (today.isBefore(fpuja)) {
                %>
                <tr><td colspan="2"><a href="AddProductoFavorito?userid=<%= usuario.getUserId()%>&prodid=<%= p.getProductId()%>">Producto Favorito</a></td></tr>
        </form>

    <tr align="center">
        <td colspan = "2" align="center">
            <table align = "center" border="false" style="width:100%">

                <tr colspan = "2" align="center"><td align="center">Puja List:</td></tr>
                <% for (Puja pu : pujalist) {
                %>
                <tr><td align="center"> <%= pu.getCompradorId().getNombre()%> <%= pu.getCompradorId().getApellido()%>: <%= pu.getCantidad()%>$</td></tr>
                <%
                    }
                %>
            </table>
        <td>
    </tr>  
    <tr>
        <td colspan ="2">

            <form action = "PujaServlet?userid=<%= usuario.getUserId()%>&prodid=<%= p.getProductId()%>" method="post">
                Puja: <input type="number" name="puja" min="<%=p.getPrecio()%>" step = "0.10" value=""/> 
                <input type="submit" name="enviar"/>
            </form>
        </td>
        <%
        } else {
        %>
    <tr><td colspan ="2"> <p> Producto Comprado </p></td> </tr>
    <%
        }
    %>

    <tr>

    </tr>
</table>
</br>
<%
    }
%>

</body>
</html>
