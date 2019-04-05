<%-- 
    Document   : cambioContrasena
    Created on : 8/03/2019, 08:34:57 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false);
    HttpSession sesion = request.getSession();

    String usuario = (String) objsesion.getAttribute("usuario");
    
    String informacionCC = (String) objsesion.getAttribute("informacionCC");
    
    String informacionCCo = (String) objsesion.getAttribute("informacionCCo");
    String informacionCCi = (String) objsesion.getAttribute("informacionCCi");

    if (usuario == null) {
        if (informacionCC == null) {
            response.sendRedirect("autenticacion.jsp");
        }
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/cambioContrasena.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.8,maximun-scale=1.0, minimum-scale=0.6">

        <title>Cambio Contraseña</title>
    </head>
    <body>
        <div class="contenedor">
            <form action="cambiarContrasena" method="post">
                <div id="titulo">
                    Cambiar contraseña
                </div>
                <div id="error">
                        <label id="informacionCC"><%if (informacionCC != null) {%><%=informacionCC%><%}%></label> 
                </div>

                <div id="subtitulo">
                    <Label for="usuario">Usuario</label><br><br><br>
                    <Label for="password">Contraseña</Label><br><br><br>
                    <Label for="nPassword">Nueva contraseña (Mayor a 9 digitos)</Label><br><br><br>
                    <Label for="vPassword"> Verificar nueva contraseña</Label>
                </div>
                <div id="txt">
                    <input id="usuario" name="usuario" type="number" required placeholder="Cedula">
                    <input id="password" name="password" type="password" required placeholder="Contraseña">
                    <input id="nPassword" type="password" name="nuevoPassword" required placeholder="Nueva Contraseña">
                    <input id="vPassword" name="verificarPassword" type="password" required placeholder="Repetir Neva Contraseña">
                </div>
                <div id="error">
                        <label id="informacionCCo"><%if (informacionCCo != null) {%><%=informacionCCo%><%sesion.removeAttribute("informacionCCo");//Remueve el usuario
                            }%></label> 
                        <label id="informacionCCi"><%if (informacionCCi != null) {%><%=informacionCCi%><%sesion.removeAttribute("informacionCCi");//Remueve el usuario
                            }%></label> 
                </div>
                <div id="btn">
                    <%if(informacionCCi==null){%>
                    <input type="submit" id="botones" VALUE="Siguiente" >    
                    <input type="button" id="botones" onclick="location.href = 'autenticacion.jsp'" value="Cancelar">
                    <%}if(informacionCCi!=null){%>
                     <input type="button" id="botones" onclick="location.href = 'autenticacion.jsp'" value="Cerrar">
                     <%objsesion.removeAttribute("informacionCC");}%>
                </div>
            </form>
        </div>
    </body>
</html>
