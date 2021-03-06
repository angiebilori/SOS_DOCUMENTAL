<%-- 
    Document   : autenticacion
    Created on : 1/03/2019, 07:32:14 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto

    objsesion.removeAttribute("cambioContrasena");//Remueve el objeto

    String error = (String) objsesion.getAttribute("error"); //Obtenermos los datos del objeto
    String ok = (String) objsesion.getAttribute("ok"); //Obtenermos los datos del objeto

    if (usuario != null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("menu.jsp");
    } else {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/autenticacion.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <title>Autenticación</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="iniciar" method="POST">

                <div class="logo">
                    <img src="logo/LOGO.png" alt="No se logro cargar la imagen">
                </div>
                <div class="titulo">
                    <h1>INGRESO</h1>
                </div>
                <!--"required" sirve para no dejar campos vacios  -->
                <div>
                    <input type="number" name="idUsuario" id="caja" placeholder="Digite Usuario" required><br>
                    <input type="password" name="contrasenia" id="caja" placeholder="Digite Contraseña" required><br>
                    <input type="submit" id="btn" value="Iniciar Sesion">
                </div>
                <div class="informacion">
                    <label id="error"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");//Remueve el usuario
                            }%></label>
                        <label id="ok"><%if (ok != null) {%><%=ok%><%objsesion.removeAttribute("ok");//Remueve el usuario
                        }%></label> 
                </div>
                <div id="piepg">
                    <a href="restaurarContrasena?cambioContrasena=restaurar">Restaurar contraseña</a> 
                </div>               
            </form>
        </div>
    </body>
</html>
<%}%>
