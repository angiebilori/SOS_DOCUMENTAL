<%-- 
    Document   : registro
    Created on : 1/03/2019, 11:37:49 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
    String rol = (String) objsesion.getAttribute("rol");

    if (usuario == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    } else {
        if (rol.equals("Operario")) {//Si tiene cargo de operario no puede ingresar al modulo
            response.sendRedirect("menu.jsp");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/registro.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.6,maximun-scale=1.0, minimum-scale=0.6">
        <title>Registro de usuarios</title>
    </head>
    <body>
        <div>
            <form action="registro" method="POST">
                <div class="contenedor">

                    <div id="titulo">
                        <h1>Registro de usuarios</h1>
                    </div>

                    <div id="subtitulos">
                        <h2 for="documento">Número de cedula</h2>
                        <h2>Primer nombre</h2>
                        <h2>Segundo nombre</h2>
                        <h2>Primer apellido</h2>
                        <h2>Segundo apellido</h2>
                        <h2>Correo</h2>
                        <h2>Rol</h2>
                    </div>
                    <div id="cajas">
                        <input type="number" name="documento" id="ced" required placeholder="Cédula">
                        <input type="text" name="primNombre" required placeholder="Primer nombre">
                        <input type="text" name="segNombre" placeholder="Segundo nombre">
                        <input type="text" name="primApellido" required placeholder="Primer apellido">
                        <input type="text" name="segApellido" required placeholder="Segundo apellido">
                        <input type="email" name="correo" required placeholder="Correo">
                        <select name="rol" required>
                            <option value="1">Administrador</option>
                            <option value="2">Operario</option> 
                        </select>
                    </div>
                    <div id="error">
                        <label value><% if (request.getAttribute("label") != null) {%>
                            <%=request.getAttribute("label")%>
                            <%}%></label>
                    </div>
                    <div id="btn">
                        <input type="submit" id="botones" VALUE="Registrar" >    
                        <input type="submit" id="botones" value="Cancelar">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
