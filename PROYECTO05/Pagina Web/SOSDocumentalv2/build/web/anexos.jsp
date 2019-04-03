<%-- 
    Document   : anexos
    Created on : 7/03/2019, 10:23:57 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
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
                        <label for="idDocumento">Id documento</label>
                        <input type="number" name="idDocumento" required>
                    </div>
                    <div id="en">
                        <label for="nomDocumento">Nombre documento</label>
                        <input type="text" name="nomDocumento" required>
                    </div>
                    <div id="en">
                        <label for="version">Vesión (1,2,3..)</label>
                        <input type="number" name="version" required>
                    </div>
                    <div id="file">
                    <input id="files" type="file" onchange="processFiles(this.files)" name="anexo" required>
                    <output id="list"></output>
                    </div>
                </div>
                <div id="previsualizacion">
                    <iframe id="mm" src="" width="100%" height="100%"> </iframe>
                    <!--<embed id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                    <!--<object id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                </div>
                <div id="btn">
                    <input  id="subir" type="submit" name="btnGuardar" value="Subir">
                    <input type="button" value="Cancelar">
                </div>
            </form>
        </div>
        <br>
    </body>
</html>
