<%-- 
    Document   : index
    Created on : 07-ene-2013, 11:54:16
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda</title>
    </head>
    <body>
        <h1>Bienvenido a la aplicación Agenda</h1>
        <ul>
            <li><a href="/servlet?op=<%= ConstantesAgenda.OPERACION_VER_TODOS_CONTACTOS %>">Ver todos los contactos</a></li>
            <li><a href="/servlet?op=<%= ConstantesAgenda.OPERACION_BUSCAR_UN_CONTACTO %>">Buscar un contacto</a></li>
            <li><a href="/servlet?op=<%= ConstantesAgenda.OPERACION_ANADIR_CONTACTO_NUEVO %>">Añadir contacto nuevo</a></li>
            <li><a href="/servlet?op=<%= ConstantesAgenda.OPERACION_ELIMINAR_CONTACTO %>">Eliminar contacto</a></li>
            <li><a href="/servlet?op=<%= ConstantesAgenda.OPERACION_MODIFICAR_CONTACTO %>">Modificar contacto</a></li>
        </ul>
    </body>
</html>