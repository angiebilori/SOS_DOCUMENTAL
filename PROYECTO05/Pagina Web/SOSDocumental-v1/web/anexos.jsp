<%-- 
    Document   : anexos
    Created on : 7/03/2019, 10:23:57 AM
    Author     : PARKA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anexos</title>
    </head>
    <body>
        <div class="cuerpo">
            <form action="agregarPdf" method="post" enctype="multipart/form-data">
                <div id="titulo">
                    Anexos
                </div>
                <div id="agregarAnexo">
                    <input id="files" type="file" name="anexo" accept=".pdf">
                </div>
                <div>
                    <input type="submit" name="subir" value="Subir">
                </div>
            </form>
        </div>

    </body>
</html>
