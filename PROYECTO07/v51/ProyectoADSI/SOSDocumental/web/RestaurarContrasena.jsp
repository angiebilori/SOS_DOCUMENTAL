<%-- 
    Document   : restaurarContrasena
    Created on : 6/03/2019, 01:02:05 PM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //String cambioContrasena = request.getParameter("cambioContrasena"); //Obtenermos los datos del objeto
    String restaurar = (String) request.getAttribute("restaurar");
    String idUsuario = (String) request.getAttribute("idUsuario");
    String correo = (String) request.getAttribute("correo");
    String error = (String) request.getAttribute("errorUsu"); //Obtenermos los datos del objeto
    String ok = (String) request.getAttribute("okUsu"); //Obtenermos los datos del objeto
    
    if (restaurar == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion  
        response.sendRedirect("AutenticarUsuario.jsp");
        // }
    } else {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/restaurarContrasena.css">
        <link href="css/Titulos.css" rel="stylesheet">

        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.8">
        <title>Restaurar contraseña</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="Usuario" method="post">
                <div class="titulo">
                    Restaurar contraseña
                </div>
                <div class="cuerpo">
                    <label for="idDocumento">Cédula</label>
                    <input  id="idDocumento" name="idUsuario" type="number" required placeholder="Ingrese número de cédula" <%if(idUsuario!=null){%>value="<%=idUsuario%>"<%}%>>
                    <label for="correo">Correo</label>
                    <input id="correo" name="correo" type="email" required placeholder="Ingrese correo" <%if(correo!=null){%>value="<%=correo%>"<%}%>>
                     <%
                        if(restaurar.equals("2")){
                    %>
                    <label for="codigo">Código</label>
                    <input type="text" name="codigoVerificacion" id="codigo" required placeholder="Código de verificación">
                    <%
                        }
                        if(restaurar.equals("3")){
                    %>
                    
                    <label for="contrasena">Nueva contraseña</label>
                    <input id="contrasena" type="password" name="nuevaContrasena" required placeholder="Contraseña">
                    <label for="verificar">Verificar</label>
                    <input id="verificar" name="verificarContrasena" type="password" required placeholder="Repetir Contraseña">
                    <%
                        }
                    %>
                </div>
                <div class="informacion">
                        <label id="error"><%if (error != null) {%><%=error%><%}%></label> 
                        <label id="error"><%if (ok != null) {%><%=ok%><%}%></label> 
                </div>
                <div class="botones">
                    <%
                        if(restaurar.equals("1")){
                    %>
                    <input type="submit" id="btn" value="Solicitar" name="accion"> 
                    <%
                        }else  if(restaurar.equals("2")){
                    %>
                    <input type="submit" id="btn" value="Validar" name="accion">
                    <%
                        }else  if(restaurar.equals("3")){
                    %>
                    <input type="submit" id="btn" value="Restaurar" name="accion">
                    <%
                        }
                    %>
                    <input type="button" id="btn" onclick="location.href = 'MenuUsuario.jsp'" value="Cancelar">
                </div>
            </form>
        </div>
    </body>
</html>
<%}%>