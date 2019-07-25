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

    String error = (String) objsesion.getAttribute("error");
    String ok = (String) objsesion.getAttribute("ok");

    //
    rol = "Administrador";
    usuario = "Administrador";

    if (usuario == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("autenticacion.jsp");
    } else {
        if (!rol.equals("Administrador")) {//Si tiene cargo de operario no puede ingresar al modulo
            response.sendRedirect("menu.jsp");
        } else {

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/registro.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.5, maximun-scale=5.0, minimum-scale=0.5">

        <title>Registro de usuarios</title>
    </head>
    <body>
        <form action="Usuario" method="POST">
            <div class="contenedor">

                <div id="titulo">
                    <h1>Registro de usuarios</h1>
                </div>
                <div id="">

                </div>

                <div id="cajas">
                    <label for="idUsuarioced">Número de cedula</label>
                    <input type="number" name="idUsuario" id="idUsuario" required placeholder="Cédula">
                    
                    <label for="primn">Primer nombre</label>
                    <input type="text" name="primNombre" id="primn" required placeholder="Primer nombre">
                    
                    <label for="segn">Segundo nombre</label>
                    <input type="text" name="segNombre" id="segn" placeholder="Segundo nombre">
                    
                    <label for="prima">Primer apellido</label>
                    <input type="text" name="primApellido" id="prima" required placeholder="Primer apellido">
                    
                    <label for="sega">Segundo apellido</label>
                    <input type="text" name="segApellido" id="sega" required placeholder="Segundo apellido">
                    
                    <label for="email">Correo</label>
                    <input type="email" name="correo" id="email" required placeholder="Correo">
                    
                    <label for="idRol">Rol</label>
                    <select name="idRol" required id="idRol">
                        
                    </select>
                </div>
                <div id="informacion">
                    <label id="informacionE"><%if (error != null) {%><%=error%><%objsesion.removeAttribute("error");
                            }%></label>
                    <label id="informacionV"><%if (ok != null) {%><%=ok%><%objsesion.removeAttribute("ok");
                            }%></label>
                </div>
                <div id="btn">
                    <input type="submit" id="botones" VALUE="Registrar" name="accion" >    
                    <input type="button" id="botones" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
            </div>
        </form>
    </body>
</html>
<%}
    }
%>
