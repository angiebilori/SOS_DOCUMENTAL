<%-- 
    Document   : ReporteGlobal
    Created on : 14/03/2019, 07:52:33 AM
    Author     : Einer
--%>
<%@include file="MenuUsuario.jsp" %>
<%
    ArrayList<ReporteEspecifico> listar = (ArrayList) request.getAttribute("listarRE");
    String idDocumento = (String) request.getAttribute("idDocumentoRE");
    String fechaIni = (String) request.getAttribute("fechaIniRE");
    String fechaFin = (String) request.getAttribute("fechaFinRE");
    
    if (permisos != null && permisos.get(0).equals("0")) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.3,maximun-scale=5.0, minimum-scale=0.3">
<!--        <link type="text/css" href="css/ReporteEspecifico.css" rel="stylesheet">-->
        <link href="css/Titulos.css" rel="stylesheet">
        <link href="css/Btns.css" rel="stylesheet">
        <link href="css/EncabezadoBusqueda.css" rel="stylesheet">

        <title>Reporte Especifico</title>
    </head>
    <body>
        <header>
            <h1>Reporte Espeficifico</h1>
        </header>
        <div class="contenedorR">
            <form action="ReporteEspecifico" method="get">
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
                           name="idDocumentoRE">

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
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaIniRE">

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
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaFinRE">
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
                            <th>Id RE</th>
                            <th>Id Doc</th>
                            <th>Version</th>
                            <th>Fecha movimiento</th>
                            <th>Usuario</th>
                        </tr>

                        <%
                            for (int i = 0; i < listar.size(); i++) {
                        %>
                        <tr>
                            <td><%=listar.get(i).getIdReporteEspecifico()%></td>
                            <td><%=listar.get(i).getIdDocumento()%></td>
                            <td><%=listar.get(i).getVersionDocumento()%></td>
                            <td><%=listar.get(i).getFechaRegistroDocumento()%></td>
                            <td><%=listar.get(i).getIdUsuario()%></td>   
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