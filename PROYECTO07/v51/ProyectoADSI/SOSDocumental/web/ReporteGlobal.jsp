<%-- 
    Document   : ReporteGlobal
    Created on : 4/06/2019, 02:15:24 PM
    Author     : PARKA
--%>
<%@include file="MenuUsuario.jsp" %>
<%    ArrayList<ReporteGlobal> listar = (ArrayList) request.getAttribute("listarRG");
    String idDocumento = (String) request.getAttribute("idDocumentoRG");
    String fechaIni = (String) request.getAttribute("fechaIniRG");
    String fechaFin = (String) request.getAttribute("fechaFinRG");

    if (permisos != null && permisos.get(0).equals("0")) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" type="text/css" href="/css/consultas.css">-->
        <link href="css/Titulos.css" rel="stylesheet">
        <link href="css/Btns.css" rel="stylesheet">
        <link href="css/EncabezadoBusqueda.css" rel="stylesheet">
        <link href="css/tablaresultados.css" rel="stylesheet">


        <script type="text/javascript" src="jQuery/cargarArchivo.js"></script>
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.3,maximun-scale=5.0, minimum-scale=0.3">
        <title>Reporte Global</title>
    </head>
    <body>
        <header>
            <h1>Reporte Global</h1>
        </header>
        <div class="contenedor">
            <form action="ReporteGlobal" method="get">
                <input type="hidden" name="modulo" value="<%=modulo%>">

                <div class="inputs">
                    <label>Id Documento</label>
                    <input type="number"

                           <%
                               if (idDocumento != null) {
                           %>
                           value="<%=idDocumento%>"
                           <%
                               }
                           %>
                           name="idDocumentoRG">

                    <label>Fecha inicial</label>
                    <input type="date"
                           <%
                               if (fechaIni != null) {
                           %>
                           value="<%=fechaIni%>"
                           <%
                           } else {
                           %>
                           value="<%=fec.obtenerSoloFecha()%>" 
                           <%
                               }
                           %>
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaIniRG">

                    <label>fecha final</label>
                    <input type="date"
                           <%
                               if (fechaFin != null) {
                           %>
                           value="<%=fechaFin%>"
                           <%
                           } else {
                           %>
                           value="<%=fec.obtenerSoloFecha()%>" 
                           <%
                               }
                           %>
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaFinRG">
                </div>
                <div class="btn">
                    <input type="submit" value="Consultar" name="accion">
                </div>
                <%
                    if (listar != null) {
                %>         
                <div>
                    <table>
                        <tr>
                            <th>Id RG</th>
                            <th>Id Doc</th>
                            <th>Cantidad Descargas</th>
                            <th>cantidad Busquedas</th>
                            <th>Registro Rocumento</th>
                            <th>Ver movimientos</th>
                        </tr>

                        <%
                            for (int i = 0; i < listar.size(); i++) {
                        %>
                        <tr>
                            <td><%=listar.get(i).getIdReporteGlobal()%></td>
                            <td><%=listar.get(i).getIdDocumento()%></td>
                            <td><%=listar.get(i).getCantidadDescargas()%></td>
                            <td><%=listar.get(i).getCantidadBusquedas()%></td>
                            <td><%=listar.get(i).getFechaReporteGlobal()%></td>
                            <td><a href="ReporteEspecifico?accion=Consultar&idDocumentoRE=<%=listar.get(i).getIdDocumento()%>&fechaIniRE=<%=listar.get(i).getFechaReporteGlobal()%>&fechaFinRE=<%=fec.obtenerSoloFecha()%>">Movimientos</a></td>
                        </tr>
                        <%
                            }
                        %>

                    </table>
                </div>
                <%
                    if (permisos.get(1).equals("0") || permisos.get(2).equals("0")) {
                %>
                <div class="btn">
                    <input type="submit" value="Generar" name="accion">
                </div>

                <%
                    }
                }
                %>
            </form>
        </div>
    </body>
</html>
<%}%>