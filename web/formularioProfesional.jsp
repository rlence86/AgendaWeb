<%@page import="com.master.agenda.utils.ConstantesAgenda"%>
<form action="servlet?op=<%= ConstantesAgenda.OPERACION_ANADIR_PROFESIONAL %>" method="post">
    Nombre: <input type="text" name="nombreContacto" /><br>
    Email: <input type="text" name="correoElectronico" /><br>
    Tel�fono: <input type="text" name="textoTelefono" /><br>
    Direcci�n: <input type="text" name="direccion" /><br>
    Empresa: <input type="text" name="empresa" /><br>
    <input type="submit" value="A�ADIR" />
</form>