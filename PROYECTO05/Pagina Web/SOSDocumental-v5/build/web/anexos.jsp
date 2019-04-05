<%-- 
    Document   : anexos
    Created on : 7/03/2019, 10:23:57 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    
    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
    String error = (String) objsesion.getAttribute("error");
    String ok = (String) objsesion.getAttribute("ok");

    if (usuario == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/anexos.css" rel="stylesheet">
        <script type="text/javascript" src="jQuery/cargarArchivo.js"></script>
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <title>Anexos</title>
    </head>
    <body>
        <div class="cuerpo">
            <form action="agregarPdf" method="post" enctype="multipart/form-data">
                <div id="titulo">
                    Anexos
                </div>
                <div id="encabezado">
                    <div id="en">
                        <label for="id">Id documento</label>
                        <input type="number" name="idDocumento" id="id" required>
                    </div>
                    <div id="en">
                        <label for="nom">Nombre documento</label>
                        <input type="text" name="nomDocumento" required id="nom">
                        
                    </div>
                    <div id="file">
                        <input id="files" type="file" onchange="processFiles(this.files)" name="documento" required>
                        <input type="text" name="tipoDocumento" id="extt">
                        <output id="list"></output>
                    </div>
                    <div id="en">
                        <label for="seleccionar">Seleccionar </label>
                        <select id="seleccionar" required="" name="seleccion">
                            <option value="">Seleccionar</option>
                            <option value="nuevoRegistro">Nuevo registro</option>
                            <option value="actualizar">Actualizar registro</option>
                        </select>
                    </div>
                </div>
                <div class="previsualizacion">
                    <div id="frame">
                        <iframe id="mm" src="" width="100%" height="100%"></iframe>

                        <!--<iframe id="mm" src="https://view.officeapps.live.com/op/embed.aspx?src=C:\Users\PARKA\Downloads.txt"></iframe>-->
                        <!--<embed id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                        <!--<object id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                    </div>
                    <div id="bloqueopdf">
                    </div>
                </div>
                <div id="informacionA">
                    <label id="informacionE"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");
                        }%></label>
                    <label id="informacionV"><%if (ok != null) {%><%=ok%><%objsesion.removeAttribute("ok");
                        }%></label>
                </div>
                <div id="btn">
                    <input  id="subir" type="submit" name="btnGuardar" value="Subir">
                    <input type="button" id="botones" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
            </form>
        </div>
    </body>
</html>
