<%-- 
    Document   : agregarManual
    Created on : 5/04/2019, 07:01:35 PM
    Author     : APRENDIZ
--%>
<%
    HttpSession objsesion = request.getSession();
    String error = (String) objsesion.getAttribute("error");

    String usuario = (String) objsesion.getAttribute("usuario");

    //Para agregar el manual se debe cambiar la condicional "==" por "!=" y cerrar sesion e la web
    if (usuario == null) {
        response.sendRedirect("autenticacion.jsp");
    } else {

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manual</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="agregarManual" method="post" enctype="multipart/form-data">
                <center>
                    <%if (usuario == null) {%>
                    <div>
                        <h1>AGREGAR MANUAL</h1>
                    </div>
                    <div>
                        <input type="file" name="manual" accept=".pdf" required><br><br>
                        <input type="submit" value="guardar"><br><br>

                        <%if (error != null) {%>
                        <label><%=error%></label>
                        <%}
                            objsesion.removeAttribute("error");%>
                    </div>
                    <%}
                        if (usuario != null) {%>
                    <div>
                        <h1>DESCARGAR MANUAL</h1>
                    </div>
                    <div>
                        <h4>Click
                            <a href="agregarManual?opcion=descargar" > aquí</a>
                            para descargar el manual.</h4>

                        <%if (error != null) {%>
                        <label><%=error%></label>
                        <%}
                          objsesion.removeAttribute("error");%>
                    </div>
                    <% }%>
                </center>
            </form>   
        </div>

    </body>
</html>
<%}%>