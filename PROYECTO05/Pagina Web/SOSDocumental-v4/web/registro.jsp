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

    String informacionE = (String) objsesion.getAttribute("informacionE");
    String informacionV = (String) objsesion.getAttribute("informacionV");

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
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.5, maximun-scale=5.0, minimum-scale=0.5">
        
        <title>Registro de usuarios</title>
    </head>
    <body>
        <form action="registro" method="POST">
            <div class="contenedor">

                <div id="titulo">
                    <h1>Registro de usuarios</h1>
                </div>

                <div id="cajas">
                    <label for="ced">Número de cedula</label>
                    <input type="number" name="documento" id="ced" required placeholder="Cédula">
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
                    <label for="rol">Rol</label>
                    <select name="rol" required id="rol">
                        <option value="1">Administrador</option>
                        <option value="2">Operario</option> 
                    </select>
                </div>
                <div id="informacion">
                        <label id="informacionE"><%if (informacionE != null) {%><%=informacionE%><%objsesion.removeAttribute("informacionE");
                                }%></label>
                        <label id="informacionV"><%if (informacionV != null) {%><%=informacionV%><%objsesion.removeAttribute("informacionV");
                                }%></label>
                </div>
                <div id="btn">
                    <input type="submit" id="botones" VALUE="Registrar" >    
                    <input type="button" id="botones" onclick="location.href = 'menu.jsp'" value="Salir">
                </div>
            </div>
        </form>
    </body>
</html>
