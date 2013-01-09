<%-- 
    Document   : formularioBusqueda
    Created on : 09-ene-2013, 9:43:01
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String objetivo = "";
  String nombreBoton = "";
  String titulo = "";
    if(request.getAttribute("objetivo").equals("busqueda")){
        objetivo = ConstantesAgenda.OPERACION_BUSCAR_CON_NOMBRE;
        nombreBoton = "Buscar";
        titulo = "Búsqueda por nombre de usuario";
    } else if(request.getAttribute("objetivo").equals("eliminar")){
        objetivo = ConstantesAgenda.OPERACION_ELIMINAR_CON_NOMBRE;
        nombreBoton = "Eliminar";
        titulo = "Eliminar por nombre de usuario";
    } else if(request.getAttribute("objetivo").equals("modificar")){
        objetivo = ConstantesAgenda.OPERACION_MODIFICAR_CON_NOMBRE;
        nombreBoton = "Modificar";
        titulo = "Modificar por nombre de usuario";
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= titulo %></title>
    </head>
    <body>
        <h1><%= titulo %></h1>
        <form action="servlet" method="post">
            <label for="nombreContacto">Nombre: </label>
            <input type="text" name="nombreContacto" id="nombreContacto" />
            <input type="hidden" name="op" value ="<%= objetivo %>" />
            <input type="submit" value="<%= nombreBoton %>" />
        </form>
    </body>
</html>