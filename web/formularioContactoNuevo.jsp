<%-- 
    Document   : formularioContactoNuevo
    Created on : 12-ene-2013, 11:58:20
    Author     : Ramón Lence <rlence86@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo contacto</title>
        <script type="text/javascript">
            var xhr;
            function obtieneRequest()
            {
                //internet explorer
                if(window.ActiveXObject)
                    xhr = new ActiveXObject("Microsoft.XMLHttp");
                //resto de navegadores
                else if((window.XMLHttpRequest)||(typeof XMLHttpRequest)!= undefined)
                    xhr = new XMLHttpRequest();
                //no soporta ajax
                else{
                    alert("su navegador no soporta AJAX");
                    return;    				
                }
            }
            function cambiaCombo()
            {
                obtieneRequest();
                enviaPeticion();
            }
            function enviaPeticion(){
                var tipoElegido = document.forms["seleccion"]["tipo"].value;
                //preparar peticion
                if(tipoElegido != 0){
                    if(tipoElegido == 1){
                        xhr.open("get", "formularioAmigo.jsp", true);
                    } else if(tipoElegido ==2){
                        xhr.open("get", "formularioProfesional.jsp", true);
                    }
                    //preparar procesamiento de la respuesta
                    xhr.onreadystatechange = procesarRespuesta;
                    xhr.send(null);
                } else {
                    document.getElementById("containerFormulario").innerHTML = "<h3>Selecciona un tipo de contacto</h3>";
                }
            }
            function procesarRespuesta()
            {
                if(xhr.readyState==4){
                    document.getElementById("containerFormulario").innerHTML = xhr.responseText;
                }
            }
        </script>
    </head>
    <body>
        <h1>Crear nuevo contacto</h1>
        <form name="seleccion">
            <select name="tipo" onchange="cambiaCombo();">
                <option value="0" selected></option>
                <option value="1">Amigo</option>
                <option value="2">Profesional</option>
            </select>
        </form>
        <div id="containerFormulario"><h3>Selecciona un tipo de contacto</h3>
        </div>
    </body>
</html>
