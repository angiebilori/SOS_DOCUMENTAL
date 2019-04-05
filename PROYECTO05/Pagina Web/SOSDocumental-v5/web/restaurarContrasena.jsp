<%-- 
    Document   : restaurarContrasena
    Created on : 6/03/2019, 01:02:05 PM
    Author     : PARKA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"

    //String cambioContrasena = request.getParameter("cambioContrasena"); //Obtenermos los datos del objeto
    String cambioContrasena = (String) objsesion.getAttribute("cambioContrasena");
    String error = (String) objsesion.getAttribute("error"); //Obtenermos los datos del objeto

    if (cambioContrasena == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion  
        //if(cambioContrasenaServlet == null){
        response.sendRedirect("autenticacion.jsp");
        // }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/restaurarContrasena.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.8">
        <title>Restaurar contraseña</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="restaurarContrasena" method="post">
                <div class="titulo">
                    Restaurar contraseña
                </div>
                <div class="cuerpo">
                    <label for="idDocumento">Cedula</label>
                    <input  id="idDocumento" name="cedula" type="number" required placeholder="Cedula">
                    <label for="correo">Correo</label>
                    <input id="correo" name="email" type="email" required placeholder="Correo">
                    <label for="contrasena">Nueva contraseña</label>
                    <input id="contrasena" type="password" name="contrasena" required placeholder="Contraseña">
                    <label for="verificar">Verificar</label>
                    <input id="verificar" name="verificacion" type="password" required placeholder="Repetir Contraseña">
                </div>
                <div class="informacion">
                        <label id="error"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");//Remueve el usuario
                        }%></label> 
                </div>
                <div class="botones">
                    <input type="submit" id="btn" VALUE="Siguiente" >    
                    <input type="button" id="btn" onclick="location.href = 'autenticacion.jsp'" value="Cancelar">
                </div>
            </form>
        </div>
    </body>
</html>
