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
        <title>Menu</title>
    </head>
    <body>
        <form action="cerrarSesion" method="post">
            <h1>Bienvenid@ <%out.println(usuario);%></h1>
            <h2>Cargo: <%out.println(rol);%> CC: <%out.println(doc);%></h2>
            <a href="cerrarSesion">Cerrar sesión</a>
            <input type="submit" value="Cerrar Sesión">
            <a href="registro.jsp">Registro</a>
            <a href="anexos.jsp">Anexos</a>
            <a href="cambioContrasena.jsp">Cambiar contraseña</a>
            <a href="consultas.jsp">Busquedas</a>
            <a href="reportes.jsp">Reportes</a>

        </form> 
    </body>
</html>
