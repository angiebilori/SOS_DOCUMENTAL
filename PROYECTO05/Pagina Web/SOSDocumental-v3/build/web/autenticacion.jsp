<%-- 
    Document   : autenticacion
    Created on : 1/03/2019, 07:32:14 AM
    Author     : Einer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
    String usuario = (String) objsesion.getAttribute("usuario"); //Obtenermos los datos del objeto
    
    objsesion.removeAttribute("informacionCC");//Remueve el usuario
    objsesion.removeAttribute("restaurar");//Remueve el usuario

    String informacionA = (String) objsesion.getAttribute("informacionA"); //Obtenermos los datos del objeto
      String informacionC = (String) objsesion.getAttribute("informacionC"); //Obtenermos los datos del objeto

    if (usuario != null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        response.sendRedirect("menu.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/autenticar.css">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <title>Autenticación</title>
    </head>
    <body>
        <div>
            <form action="iniciar" method="POST">
                <div class="contenedor">
                    <div class="logo">
                        <img src="logo/LOGO.png" alt="No se logro cargar la imagen">
                    </div>
                    <h1>INGRESO</h1>
                    <!--"required" sirve para no dejar campos vacios  -->
                    <div>
                        <input type="number" name="usuario" id="caja" placeholder="Digite Usuario" required><br>
                        <input type="password" name="password" id="caja" placeholder="Digite Contraseña" required><br>
                        <input type="submit" id="btn" value="Iniciar Sesion">
                    </div>
                    <div id="error">
                        <label id="informacionA"><%if (informacionA != null) {%><%=informacionA%><%objsesion.removeAttribute("informacionA");//Remueve el usuario
                            }%></label>
                           <label id="informacionC"><%if (informacionC != null) {%><%=informacionC%><%objsesion.removeAttribute("informacionC");//Remueve el usuario
                            }%></label> 
                    </div>
                    <div id="piepg">
                        <a href="envioARestaurarContra">Restaurar contraseña</a> 
                    </div>
                </div>                 
            </form>
        </div>
    </body>
</html>
