<%-- 
    Document   : cambioContrasena
    Created on : 8/03/2019, 08:34:57 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false);

    String usuario = (String) objsesion.getAttribute("usuario");

    String cambioContrasena = (String) objsesion.getAttribute("cambioContrasena");

    String error = (String) objsesion.getAttribute("error");
    String ok = (String) objsesion.getAttribute("ok");

    if (usuario == null & cambioContrasena == null) {
        response.sendRedirect("autenticacion.jsp");
    } else {

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/cambioContrasena.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.6">

        <title>Cambio Contraseña</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="cambiarContrasena" method="post">
                <div class="titulo">
                    <h1>CAMBIAR CONTRASEÑA</h1>
                </div>
                <div class="informacion">
                    <label id="error"><%if (cambioContrasena != null) {%><%=cambioContrasena%><%}%></label> 
                </div>

                <div class="encabezado">

                    <label for="usuario">Usuario</label>
                    <input id="usuario" name="usuario" type="number" required placeholder="Cedula">

                    <label for="password">Contraseña</label>
                    <input id="password" name="password" type="password" required placeholder="Contraseña">

                    <label for="nPassword">Nueva contraseña</label>
                    <input id="nPassword" type="password" name="nuevoPassword" required placeholder="Nueva Contraseña">

                    <label for="vPassword"> Verificar nueva contraseña</label>
                    <input id="vPassword" name="verificarPassword" type="password" required placeholder="Repetir Neva Contraseña">
                </div>
                <div class="informacion">
                    <label id="error"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");//Remueve el usuario
                        }%></label> 
                    <label id="ok"><%if (ok != null) {%><%=ok%><%objsesion.removeAttribute("ok");//Remueve el usuario
                        }%></label> 
                </div>
                <div class="botones">
                    <%if (ok == null) {%>
                    <input type="submit" VALUE="Siguiente" >    
                    <input type="button" onclick="location.href = 'autenticacion.jsp'" value="Cancelar">
                    <%}
                        if (ok != null) {%>
                    <input type="button" onclick="location.href = 'autenticacion.jsp'" value="Cerrar">
                    <%objsesion.removeAttribute("cambioContrasena");
                        }%>
                </div>
            </form>
        </div>
    </body>
</html>
<%}%>
