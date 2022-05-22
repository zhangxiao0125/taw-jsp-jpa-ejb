<%-- 
    Document   : registration
    Created on : May 14, 2022, 12:09:10 PM
    Author     : cecil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="g21.entity.Categoria"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <% 
            List<Categoria> categoria = (List)request.getAttribute("categoria");
            String message = (String)request.getAttribute("message");
            
        %>
    </head>
    <body>
        <h1>Registración Comprador</h1>
        <%
            if(message!=null)
            {
        %>
        <p><%= message %></p>
        <%
            }
        %>
        <form method="post" action="RegistrationServlet">
            Nombre: </br><input type="text" name="nombre" value="" /></br>
            Apellido:  </br><input type="text" name="apellido" value="" /></br>
            Email: </br><input type="text" name="email" value="" /></br>
            Domicilio: </br> <input type="text" name="domicilio" value="" /></br>
            Ciudad de Residencia:</br>  <input type="text" name="ciudad" value="" /></br>
            Edad: </br> <input type="number" name="edad" value="" /></br>
            Sexo:</br>
            <input type="radio" name="sexo" value="H" />H</br>
            <input type="radio" name="sexo" value="M" />M</br>
            Categorias Preferidas:</br>
        
            <%for(Categoria c: categoria)
            {
            %>
            <input type="checkbox" name = "cat" value="<%= c.getNombre()%>"/> <%= c.getNombre() %> </br>
            <%
            }
            %>
            <input type="submit" value="enviar"/>
        </form>
    </body>
</html>
