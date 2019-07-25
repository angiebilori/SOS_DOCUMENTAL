<%-- 
    Document   : cambioContrasena
    Created on : 8/03/2019, 08:34:57 AM
    Author     : Einer
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String error = (String) request.getAttribute("errorUsu");
    String ok = (String) request.getAttribute("okUsu");
    String contrasena = (String) request.getAttribute("contrasena"); //Obtenermos los datos del objeto
    String idUsuario = (String) request.getAttribute("idUsuario");
    
    if(contrasena==null){
        contrasena = (String) request.getParameter("contrasena");
         idUsuario = (String) request.getParameter("idUsuario");
    }
    if ((contrasena != null && contrasena.equals("0"))) {
        //objSesion.setAttribute("con","0");
%>
    <%@include file="MenuUsuario.jsp" %>
<%            
    }
    //ArrayList permisos = (ArrayList) objSesion.getAttribute("permisos");
    if (contrasena != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/validarDatos.js"></script>
        <!--<link type="text/css" href="css/cambioContrasena.css" rel="stylesheet">-->
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.6">
         <link href="css/Titulos.css" rel="stylesheet">
         <link href="css/Btns.css" rel="stylesheet">
         <link href="css/EncabezadoBusqueda.css" rel="stylesheet">
        <title>Cambio Contraseña</title>
    </head>
    <body>
        <div class="conteneor">
            <form action="Usuario" method="post">
                <header>
                    <h1>Cambiar Contraseña</h1>
                </header>
                <div class="informacion">
                    <label id="error"><%if (contrasena != null && !contrasena.equals("0")) {%><%=contrasena%><%}%></label> 
                </div>

                <div class="encabezado">

                    <label for="idUsuario">Usuario</label>
                    <input id="idUsuario" name="Usuario" type="number" required <%if (idUsuario != null) {%>value="<%=idUsuario%>"<%}%> disabled placeholder="Usuario">
                    <input type="hidden" name="idUsuario"  <%if (idUsuario != null) {%>value="<%=idUsuario%>"<%}%>>

                    <label for="contrasena">Contraseña</label>
                    <input id="contrasena" name="contrasena" type="password" required placeholder="Contraseña">

                    <label for="nPassword">Nueva contraseña</label>
                    <input id="nPassword" type="password" name="nuevaContrasena" required placeholder="Nueva Contraseña">

                    <label for="vPassword"> Verificar nueva contraseña</label>
                    <input id="vPassword" name="verificarContrasena" type="password" required placeholder="Repetir Nueva Contraseña">
                </div>
                <div class="informacion">
                    <label id="error"><%if (error != null) {%><%=error%><%}%></label> 
                    <label id="ok"><%if (ok != null) {%><%=ok%><%}%></label> 
                </div>
                <div class="botones">
                    <%if (ok == null) {%>
                    <input type="submit" value="Cambiar" name="accion">  
                    <input type="button" onclick="location.href = 'MenuUsuario.jsp'" value="Cancelar">
                    <%}
                        if (ok != null) {%>
                    <input type="button" onclick="location.href = 'MenuUsuario.jsp'" value="Cerrar">
                    <%}%>
                </div>
            </form>
        </div>
    </body>
</html>
<%
   } else if(contrasena != null && contrasena.equals("0")){
        response.sendRedirect("MenuUsuario.jsp");
    }
%>
