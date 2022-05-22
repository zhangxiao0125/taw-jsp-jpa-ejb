<%-- 
    Document   : login
    Created on : May 15, 2022, 1:52:28 PM
    Author     : cecil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action = "LoginServlet" method="post">
            Nombre:</br><input type = "text" name = "nombre" value=""/></br>
            Apellido:</br><input type = "text" name = "apellido" value=""/></br>
            Email:</br><input type = "text" name = "email" value=""/></br>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
