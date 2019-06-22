<%-- 
    Document   : ReporteDescargas
    Created on : 5/06/2019, 10:51:02 AM
    Author     : PARKA
--%>
<%@include file="MenuUsuario.jsp" %>

<%    ArrayList<Descarga> listar = (ArrayList) request.getAttribute("listarDes");
    String idDocumento = (String) request.getAttribute("idDocumentoDes");
    String fechaIni = (String) request.getAttribute("fechaIniDes");
    String fechaFin = (String) request.getAttribute("fechaFinDes");

    if (permisos != null && permisos.get(0).equals("0")) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Descargas</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="ReporteDescargas" method="get">
                <input type="hidden" name="modulo" value="<%=modulo%>">
                <div class="titulo">
                    <h1>Reporte Descargas</h1>
                </div>
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
                           name="idDocumentoDes">

                    <label>Fecha inicial</label>
                    <input type="date"
                           <%
                               if (fechaIni != null) {
                           %>
                           value="<%=fechaIni%>"
                           <%                       } else {
                           %>
                           value="<%=fec.obtenerSoloFecha()%>" 
                           <%                              }
                           %>
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaIniDes">

                    <label>fecha final</label>
                    <input type="date"
                           <%
                               if (fechaFin != null) {
                           %>
                           value="<%=fechaFin%>"
                           <%                       } else {
                           %>
                           value="<%=fec.obtenerSoloFecha()%>" 
                           <%                              }
                           %>
                           max="<%=fec.obtenerSoloFecha()%>" name="fechaFinDes">
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
                            <th>Id DES</th>
                            <th>Id Doc</th>
                            <th>Fecha Descarga</th>
                            <th>Usuario</th>
                        </tr>

                        <%
                            for (int i = 0; i < listar.size(); i++) {
                        %>
                        <tr>
                            <td><%=listar.get(i).getIdDescarga()%></td>
                            <td><%=listar.get(i).getIdDocumento()%></td>
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
