<%-- 
    Document   : NavegadorNoCompatible
    Created on : 5/06/2019, 12:38:21 PM
    Author     : PARKA
--%>
<%
    if (request.getParameter("incompatible") != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Navegador Incompatible</title>
    </head>
    <body>
        <h1>Este navegador No es compatible con esta aplicacion web.</h1>
        <h3>Por favor utilizar Chrome o Firefox</h3>
        <a href="https://www.google.com.mx/intl/es-419/chrome/">Descarage Chrome</a>
        <br>
        <a href="https://www.mozilla.org/es-ES/firefox/new/">Descargar Firefox</a>
    </body>
</html>
<%    } else {
        response.sendRedirect("MenuUsuario?accion=cerrarSesion");
    }
%>
