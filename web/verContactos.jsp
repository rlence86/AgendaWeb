<%-- 
    Document   : verContactos
    Created on : 09-ene-2013, 9:00:51
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page import="com.ramonlence.agenda.logica.Profesional"%>
<%@page import="com.ramonlence.agenda.logica.Amigo"%>
<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<%@page import="java.util.List"%>
<%@page import="com.ramonlence.agenda.logica.Contacto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Contacto> contactos = (List<Contacto>)request.getAttribute(ConstantesAgenda.ATRIBUTO_CONTACTOS); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Contactos encontrados</h1>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Teléfono</th>
                <th>Cumpleaños</th>
                <th>Dirección</th>
                <th>Empresa</th>
                <th>Tipo</th>
            </tr>
            <% for(Contacto con:contactos) {
                if(con instanceof Amigo){ %>
                    <td><%= con.getNombre() %></td>
                    <td><%= con.getEmail() %></td>
                    <td><%= con.getTelefono() %></td>
                    <td><%= ((Amigo)con).getFechaCumple() %></td>
                    <td></td>
                    <td></td>
                    <td>Amigo</td>
            <%  } else if(con instanceof Profesional) { %>
                    <td><%= con.getNombre() %></td>
                    <td><%= con.getEmail() %></td>
                    <td><%= con.getTelefono() %></td>
                    <td></td>
                    <td><%= ((Profesional)con).getDireccion() %></td>
                    <td><%= ((Profesional)con).getEmpresa() %></td>
                    <td>Profesional</td>
            <%  }
            }
                
    
    
    %>
        </table>
    </body>
</html>
