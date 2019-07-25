<%-- 
    Document   : autenticacion
    Created on : 1/03/2019, 07:32:14 AM
    Author     : Einer
--%>

<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    ArrayList<Usuario> datosUsu = (ArrayList) objsesion.getAttribute("datosUsu"); //Obtenermos los datos del objeto

    //objsesion.removeAttribute("restaurar");//Remueve el objeto

    String error = (String) request.getAttribute("errorUsu"); //Obtenermos los datos del objeto
    String ok = (String) request.getAttribute("okUsu"); //Obtenermos los datos del objeto

    String navegador = request.getHeader("USER-AGENT");
    String ipUsuarios = request.getRemoteAddr();

    //Para identificar el tipo de navegador
    if (navegador.indexOf("Chrome") > -1 || navegador.indexOf("Firefox") > -1) {

        if (datosUsu != null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
            response.sendRedirect("MenuUsuario?accion=recargarMenu");
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
            <form action="Usuario" method="GET">

                <div class="logo">
                    <img src="logo/LOGO.png" alt="No se logro cargar la imagen">
                </div>
                <div class="titulo">
                    <h1>INGRESO</h1>
                </div>
                <!--"required" sirve para no dejar campos vacios  -->
                <div>
                    <input type="number" name="idUsuario" id="caja" placeholder="Digite Usuario" required><br>
                    <input type="password" name="contrasena" id="caja" placeholder="Digite Contraseña" required><br>
                    <input type="submit" id="btn" value="Iniciar Sesion" name="accion">
                </div>
                <div class="informacion">
                    <label id="error"><%if (error != null) {%><%=error%><%}%></label>
                    <label id="ok"><%if (ok != null) {%><%=ok%><%}%></label> 
                </div>
                <div id="piepg">
                    <a href="Usuario?accion=solicitarRestaurar">Restaurar contraseña</a> 
                </div>               
            </form>
        </div>
    </body>
</html>
<%
        }
    } else {
        response.sendRedirect("NavegadorNoCompatible.jsp?incompatible=1");
    }
%>
