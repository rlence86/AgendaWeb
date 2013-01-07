<%-- 
    Document   : login
    Created on : 07-ene-2013, 8:41:13
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilos.css" />
        <title>Login Page</title>
    </head>
    <body>
        <h1 class="titulo">Introduce tu nombre</h1>
        <form action="servlet" method="post">
            <input type="text" name="nombreUsuario" />
            <input type="hidden" name="op" value="0" />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
