<%-- 
    Document   : formularioModificacion
    Created on : 09-ene-2013, 11:40:25
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<%@page import="com.master.agenda.logica.Contacto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Contacto contacto = (Contacto)request.getAttribute("contacto"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar contacto</title>
    </head>
    <body>
        <h1>Modificar contacto</h1>
        <form action="servlet" method="post">
            <label for="telefono">Telefono:</label>
            <input type="text" value="<%= contacto.getTelefono() %>" name="telenofoText" id="telefono" /><br>
            <label for="email">Correo electrónico:</label>
            <input type="text" value="<%= contacto.getEmail() %>" name="emailText" id="email" />
            <input type="hidden" name="op" value ="<%= ConstantesAgenda.OPERACION_MODIFICAR_CON_DATOS %>" />
            <input type="submit" value="Modificar" />
        </form>
    </body>
</html>