<%-- 
    Document   : menu
    Created on : 1/03/2019, 11:37:29 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"

    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
    String rol = (String) objsesion.getAttribute("rol");
    String doc = (String) objsesion.getAttribute("idUsuario");

    //Cuando ingrese al menu se borran los datos utilizados en otros modulos
    objsesion.removeAttribute("datos");
    objsesion.removeAttribute("datosA");
    objsesion.removeAttribute("error");
    objsesion.removeAttribute("ok");
    objsesion.removeAttribute("cambioContrasena");

    if (usuario == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/menu.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <title>Menu</title>
    </head>
    <body>
        <div class="contenedor">     
            <form action="cerrarSesion" method="post">
                <div class="logo">
                    <a href="menu.jsp"><img src="../logo/LOGO.gif" alt="No se puede cargar la imagen"></a>
                </div>
                <ul class="menu1">
                    <li><a href="consultas.jsp" target="ventana_iframe">Busquedas</a></li>
                    <li><a href="anexos.jsp" target="ventana_iframe">Documento</a></li>
                    <li><a href="reportes.jsp" target="ventana_iframe">Reportes</a></li>
                    <li><a href="">Ayuda</a></li>
                    <li><a href=""><%out.println(usuario);%></a>
                        <ul class="menu2">
                            <li id="usuario">CC: <%out.println(doc);%> <br> Rol: <%out.println(rol);%></li>
                            <li><a href="registro.jsp" target="ventana_iframe">Nuevo Registro</a></li>
                            <li><a href="cambioContrasena.jsp" target="ventana_iframe">Cambiar Contraseña</a></li>
                            <li><a href="cerrarSesion">Cerar Sesión</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="iframe">
                    <iframe id="frame" name="ventana_iframe" scrolling="yes" >
                    </iframe> 
                </div>
            </form>   
        </div>
    </body>
</html>
