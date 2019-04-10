<%-- 
    Document   : reportes
    Created on : 2/04/2019, 12:01:12 PM
    Author     : Einer
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession();

    String usuario = (String) objsesion.getAttribute("usuario");
    ArrayList<String> datos = (ArrayList) objsesion.getAttribute("datos");

    String ok = (String) objsesion.getAttribute("ok");
    String error = (String) objsesion.getAttribute("error");

    if (usuario == null) {
        response.sendRedirect("autenticacion.jsp");
    } else {

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/reportes.css" rel="stylesheet">

        <title>JSP Page</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="reporte" method="post">
                <div class="titulo">
                    <h1>REPORTES</h1>
                </div>
                <div class="encabezado">
                    <label for="idDocumento">Id Documento</label>
                    <input type="numer" name="idDocumento" id="idDocumento" required>

                    <label for="tipoReporte">Seleccionar el tipo de reporte</label>
                    <select name="tipoReporte" id="tipoReporte" required="">
                        <option value="">Seleccionar</option>
                        <option value="reporteEspecifico">Reporte específico</option>
                        <option value="reporteGlobal">Reporte Global</option>
                    </select>
                </div>
                <div class="botones">
                    <input type="submit" name="consultar" value="Consultar">
                    <input type="button" name="Salir" value="salir" onclick="location.href = 'menu.jsp'">
                </div>
                <%if (datos != null) {%>
                <div id="resultado">
                    <table>
                        <tr id="tituloTabla">
                            <th colspan="6">Reporte Específico</th>                           
                        </tr>
                        <tr id="encabezadoTabla">
                            <th>Código Documento</th>                           
                            <th>Nombre Documento</th>
                            <th>Fecha actualización</th>
                            <th>Id Usuario</th>
                            <th>Nombres Usuario</th>

                            <th>Version Actualización</th>
                        </tr>
                        <%for (int i = 0; i < datos.size(); i += 6) {%>
                        <tr>
                            <td><%=datos.get(i)%></td>
                            <td><%=datos.get(i + 1)%></td>
                            <td><%=datos.get(i + 2)%></td>
                            <td><%=datos.get(i + 3)%></td>
                            <td><%=datos.get(i + 4)%></td>
                            <td><%=datos.get(i + 5)%></td>                            
                        </tr>
                        <%}%>
                    </table>
                </div>
                <%}%>
                <div class="informacion">
                    <!-- <input type="button" name="Salir" value="Exportar reporte" onclick="location.href = ''">-->

                    <%if (ok != null) {%>
                    <label id="ok"><%=ok%></label>
                    <%}
                        if (error != null) {%>
                    <label id="error"><%=error%></label>
                    <%}
                        objsesion.removeAttribute("ok");
                        objsesion.removeAttribute("error");
                        objsesion.removeAttribute("datos");
                        objsesion.removeAttribute("datosA");
                    %>
                </div>
            </form>
        </div>
    </body>
</html>
<%}%>
