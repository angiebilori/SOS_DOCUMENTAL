<%-- 
    Document   : restaurarContrasena
    Created on : 6/03/2019, 01:02:05 PM
    Author     : PARKA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    String restaurar = (String) objsesion.getAttribute("restaurar"); //Obtenermos los datos del objeto
    if (restaurar != null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/restaurarContrasena.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.6">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="restaurarContrasena" method="post">
            <div class="contenedor">
                <div id="titulo">
                    Restaurar contraseña
                </div>
                <div id="subtitulo">
                    <h2>Cedula</h2>
                    <h2>Correo</h2>
                    <h2>Nueva contraseña</h2>
                    <h2> Verificar</h2>
                </div>
                <div id="txt">
                    <input  name="cedula" type="number" required placeholder="Cedula">
                    <input  name="email" type="text" required placeholder="Correo">
                    <input type="password" name="contrasena" required placeholder="Contraseña">
                    <input  name="verificacion" type="password" required placeholder="Contraseña">
                </div>
                <div id="error">
                    <label value><% if (request.getAttribute("label") != null) {%>
                        <%=request.getAttribute("label")%>
                        <%}%></label>
                </div>
                <div id="btn">
                    <input type="submit" id="botones" VALUE="Siguiente" >    
                    <input type="button" id="botones" onclick="location.href = 'autenticacion.jsp'" value="Cancelar">
                </div>
            </div>
        </form>
    </body>
</html>
