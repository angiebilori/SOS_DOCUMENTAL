<%-- 
    Document   : consultas
    Created on : 14/03/2019, 07:52:33 AM
    Author     : Einer
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false);

    String usuario = (String) objsesion.getAttribute("usuario");
    String ok = (String) objsesion.getAttribute("ok");
    String error = (String) objsesion.getAttribute("error");

    ArrayList<String> datos = (ArrayList<String>) objsesion.getAttribute("datos");
    ArrayList<String> datosA = (ArrayList<String>) objsesion.getAttribute("datosA");

    if (usuario == null) {
        //response.sendRedirect("autenticacion.jsp");
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/consultas.css" rel="stylesheet">

        <script type="text/javascript" src="jQuery/cargarArchivo.js"></script>
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.3,maximun-scale=5.0, minimum-scale=0.3">
        <title>Consultas</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="busqueda" method="Post">
                
                <div class="titulo">Búsquedas</div>
                <div class="encabezado">
                    <label for="txtIdNom">Código o nombre documento</label>    
                    <input type="text" name="txtIdNom" required="" placeholder="Ingresa código documento">
                    <!--
                    <label for="fecha">Fecha en que se guardo el documento</label>
                    <input type="date" name="fecha" id="fecha">
                    -->
                </div>
                <div class="botones">
                    <input type="submit" id="btn" value="Buscar" name="btnBuscar">
                    <input type="button" id="btn" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
                <% if (datosA != null) {%>
                <div class="resultado">
                    <table>
                        <tr>
                            <th colspan="7">Documento(s) Con actualizaciones recientes</th>                           
                        </tr>
                        <tr>
                            <th>Código Documento</th>                           
                            <th>Nombre Documento</th>
                            <th>Fecha actualización</th>
                            <th>Usuario-registro</th>
                            <th>Version</th>
                            <th>Tipo Documento</th>
                            <th>Descargar</th>
                        </tr>
                        <%for (int i = 0; i < datosA.size(); i += 6) {%>

                        <td><%=datosA.get(i)%></td>                           
                        <td><%=datosA.get(i + 1)%></td>
                        <td><%=datosA.get(i + 2)%></td>
                        <td><%=datosA.get(i + 3)%></td>
                        <td><%=datosA.get(i + 4)%></td>
                        <td><%=datosA.get(i + 5)%></td>
                        <td><a href="descargar?des=act&idDocumento=<%=datosA.get(i)%>">Descargar</a></td>

                        <%}%>
                    </table>
                </div>
                <%}
                    if (datos != null) {%>
                <div class="resultado">
                    <table>
                        <tr>
                            <th>Código Documento</th>                           
                            <th>Nombre Documento</th>
                            <th>Fecha del documento</th>
                            <th>Usuario-registro</th>
                            <th>Versión</th>
                            <th>Tipo Documento</th>
                            <th>Descargar</th>
                        </tr>
                        <%for (int j = 0; j < datos.size(); j += 6) {%>

                        <td><%=datos.get(j)%></td>                           
                        <td><%=datos.get(j + 1)%></td>
                        <td><%=datos.get(j + 2)%></td>
                        <td><%=datos.get(j + 3)%></td>
                        <td><%=datos.get(j + 4)%></td>
                        <td><%=datos.get(j + 5)%></td>
                        <td><a href="descargar?des=doc&idDocumento=<%=datos.get(j)%>">Descargar</a></td>


                        <%}%> </table>
                </div><%}%>
                <div class="informacion">
                    <label id="ok"><%if (ok != null) {%>
                        <%=ok%>
                        <%objsesion.removeAttribute("ok");
                            }%>
                    </label>
                    <label id="error"><%if (error != null) {%>
                        <%=error%>
                        <%objsesion.removeAttribute("error");
                            }%>
                    </label>
                </div>
            </form>
        </div>
    </body>
</html>