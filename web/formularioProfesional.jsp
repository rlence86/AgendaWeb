<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<form action="servlet?op=<%= ConstantesAgenda.OPERACION_ANADIR_PROFESIONAL %>" method="post">
    Nombre: <input type="text" name="nombreContacto" /><br>
    Email: <input type="text" name="correoElectronico" /><br>
    Teléfono: <input type="text" name="textoTelefono" /><br>
    Dirección: <input type="text" name="direccion" /><br>
    Empresa: <input type="text" name="empresa" /><br>
    <input type="submit" value="AÑADIR" />
</form>