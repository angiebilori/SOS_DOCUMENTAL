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
    } else {
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
        <div class="contenedor">
            <form action="agregarPdf" method="post" enctype="multipart/form-data">
                <div class="titulo">
                    <h1>DOCUMENTOS</h1>
                </div>
                <div class="encabezado">
                    <label for="id">Id documento</label>
                    <input type="number" name="idDocumento" id="id" required>

                    <label for="nom">Nombre documento</label>
                    <input type="text" name="nomDocumento" required id="nom">

                    <label for="files">Adjuntar archivo</label>
                    <input id="files" type="file" onchange="processFiles(this.files)" name="documento" required placeholder="">


                    <label for="files">Extencion del archivo</label>
                    <div class="tipoDocumento">
                        <input type="text" name="tipoDocumento" id="extt">
                        <div id="bloqueartxt"></div>
                    </div>
                    <!--<output id="list"></output>-->
                    <label for="seleccionar">Registro o Actualizazión</label>
                    <select id="seleccionar" required="" name="seleccion">
                        <option value="">Seleccionar</option>
                        <option value="nuevoRegistro">Nuevo registro</option>
                        <option value="actualizar">Actualizar registro</option>
                    </select>
                </div>

                <div class="previsualizacion">
                    <div id="iframe">
                        <iframe id="mm" src=""></iframe>
                        <!--<iframe id="mm" src="https://view.officeapps.live.com/op/embed.aspx?src=C:\Users\PARKA\Downloads.txt"></iframe>-->
                        <!--<embed id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                        <!--<object id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                    </div>
                    <div id="bloquearpdf">
                    </div>
                </div>
                <div class="informacion">
                    <label id="error"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");
                        }%></label>
                    <label id="ok"><%if (ok != null) {%><%=ok%><%objsesion.removeAttribute("ok");
                        }%></label>
                </div>
                <div class="botones">
                    <input type="submit" name="btnGuardar" value="Subir">
                    <input type="button" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
            </form>
        </div>
    </body>
</html>
<%}%>