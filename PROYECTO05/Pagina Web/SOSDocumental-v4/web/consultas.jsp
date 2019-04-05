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
    String informacion = (String) objsesion.getAttribute("informacion");

    ArrayList<String> datos = (ArrayList<String>) objsesion.getAttribute("datos");
    ArrayList<String> datosA = (ArrayList<String>) objsesion.getAttribute("datosA");

    //if (usuario == null) {
    //  response.sendRedirect("autenticacion.jsp");
    //}

%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" href="css/consultas.css" rel="stylesheet">
        <script type="text/javascript" src="jQuery/cargarArchivo.js"></script>
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultas</title>
    </head>
    <body>
        <div class="cuerpo">
            <form action="busqueda" method="Post">
                <div id="titulo">Búsquedas</div>
                <div id="encabezado">
                    <label for="txtIdNom">Código o nombre documento</label>    
                    <input type="text" name="txtIdNom" required="">

                    <label for="fecha">Fecha en que se guardo el documento</label>
                    <input type="date" name="fecha" id="fecha">
                </div>
                <div id="btn">
                    <input type="submit" value="Buscar" name="btnBuscar">
                    <input type="button" id="botones" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
                <% if (datosA != null) {%>
                <div id="resultado">
                    <table>
                        <tr>
                            <th>Código Documento</th>                           
                            <th>Nombre Documento</th>
                            <th>Fecha actualización</th>
                            <th>Version Actualización</th>
                            <th>Descargar</th>
                        </tr>
                        <%for (int i = 0; i < datosA.size(); i += 4) {%>

                        <td><%=datosA.get(i)%></td>                           
                        <td><%=datosA.get(i + 1)%></td>
                        <td><%=datosA.get(i + 2)%></td>
                        <td><%=datosA.get(i + 3)%></td>
                        <td><a href="descargar?des=act">Descargar</a></td>
                       
                        <%}%>
                    </table>
                </div>
                <%}
                    if (datos != null) {%>
                <div id="resultado">
                    <table>
                        <tr>
                            <th>Código Documento</th>                           
                            <th>Nombre Documento</th>
                            <th>Fecha del documento</th>
                            <th>Versión</th>
                            <th>Descargar</th>
                        </tr>
                        <%for (int j = 0; j < datos.size(); j += 4) {%>

                        <td><%=datos.get(j)%></td>                           
                        <td><%=datos.get(j + 1)%></td>
                        <td><%=datos.get(j + 2)%></td>
                        <td><%=datos.get(j + 3)%></td>
                        <td><a href="descargar?des=doc">Descargar</a></td>


                        <%}%> </table>
                </div><%
                        objsesion.removeAttribute("informacion");
                    }%>
                <div id="informacion">
                    <label><%=informacion%></label>
                </div>
            </form>
        </div>
    </body>
</html>