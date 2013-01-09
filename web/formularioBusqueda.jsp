<%-- 
    Document   : formularioBusqueda
    Created on : 09-ene-2013, 9:43:01
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Búsqueda por nombre de usuario</title>
    </head>
    <body>
        <h1>Búsqueda de contacto</h1>
        <form action="servlet" method="post">
            <label for="nombreContacto">Nombre: </label>
            <input type="text" name="nombreContacto" id="nombreContacto" />
            <input type="hidden" name="op" value ="<%= ConstantesAgenda.OPERACION_BUSCAR_CON_NOMBRE %>" />
            <input type="submit" value="Buscar" />
        </form>
    </body>
</html>