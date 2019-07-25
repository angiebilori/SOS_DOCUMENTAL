<%-- 
    Document   : menu
    Created on : 1/03/2019, 11:37:29 AM
    Author     : Einer
--%>

<%@page import="model.ClasificacionModulo"%>
<%@page import="model.Modulo"%>
<%@page import="model.Rol"%>
<%@page import="model.Perfil"%>
<%@page import="model.Usuario"%>
<%@page import="model.Documento"%>
<%@page import="model.Descarga"%>
<%@page import="model.Fecha"%>
<%@page import="model.ReporteEspecifico"%>
<%@page import="model.ReporteGlobal"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Fecha fec = new Fecha(); //Desde otros jsp

    HttpSession objSesion = request.getSession(false); //Obtener la sesion iniciada"false"

    ArrayList<Usuario> datosUsu = (ArrayList) objSesion.getAttribute("datosUsu"); //Obtenermos los datos del objeto
    ArrayList<Rol> datosRol = (ArrayList) objSesion.getAttribute("datosRol"); //Obtenermos los datos del objeto
    ArrayList<ArrayList<Modulo>> datosMod = (ArrayList) objSesion.getAttribute("datosMod"); //Obtenermos los datos del objeto
    ArrayList<ClasificacionModulo> datosClaMod = (ArrayList) objSesion.getAttribute("datosClaMod"); //Obtenermos los datos del objeto
 


    ArrayList permisos = (ArrayList) objSesion.getAttribute("permisos");//Se estan llamando de los otros jsp
    String modulo = (String) objSesion.getAttribute("modulo");//Se estan llamando de los otros jsp

    //objSesion.removeAttribute("contrasena");
    
    if (datosUsu == null) {//Si los datos obtenidos son nulos o no hay datos, redireccionamos a lapagina de Autenticacion
        
        response.sendRedirect("MenuUsuario?accion=cerrarSesion");
    } else {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/menu.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0,maximun-scale=5.0, minimum-scale=1.0">
        <title>Menu</title>
    </head>
    <body>
        <div class="contenedor">
            
            <form action="MenuUsuario" method="post">
                <div class="logo">
                    <a href="Usuario?accion=recargarUsuario&idUsuario=<%=datosUsu.get(0).getIdUsuario()%>"><img src="logo/LOGO.gif" alt="No se puede cargar la imagen"></a>
                </div>
                <div class="menu">
                    <ul class="menu1">
                        <%
                            if (datosClaMod != null) {
                                for (int i = 0; i < datosClaMod.size(); i++) {
                        %>
                        <li><a href="MenuUsuario?accion=consultar&idCla=<%=datosClaMod.get(i).getIdClasificacionModulo()%>"><%=datosClaMod.get(i).getNomClasificacionModulo()%></a>
                            <%
                                if (datosMod != null) {
                            %>
                            <ul class="menu2">
                                <%
                                    for (int j = 0; j < datosMod.size(); j++) {
                                        if ((datosMod.get(j).get(0).getIdClasificacionModulo().equals(datosClaMod.get(i).getIdClasificacionModulo()) && datosMod.get(j).get(0).getEstadoModulo().equals("Activo") && datosClaMod.get(i).getEstadoClasificacionModulo().equals("Activo"))) {
                                %>
                                <li><a href="MenuUsuario?accion=cargarModulo&idMod=<%=datosMod.get(j).get(0).getIdModulo()%>"><%=datosMod.get(j).get(0).getNomModulo()%></a></li>
                                    <%
                                    } else {//Cuando el modulo esta inactivo bloque las opciones
                                        if (datosMod.get(j).get(0).getIdClasificacionModulo().equals(datosClaMod.get(i).getIdClasificacionModulo())) {
                                    %>
                                <li id="usuario"><%=datosMod.get(j).get(0).getNomModulo()%>: Desactivado</li>
                                    <%
                                                }
                                            }
                                        }
                                    %>
                            </ul>
                            <%
                                }
                            %>
                        </li>
                        <%
                                }
                            }
                        %>

                        <li><a><%out.println(datosUsu.get(0).getPrimNombre() + " " + datosUsu.get(0).getPrimApellido());%></a>
                            <ul class="menu2">
                                <li id="usuario">CC: <%=datosUsu.get(0).getIdUsuario()%> <br> Rol: <%=datosRol.get(0).getNomRol()%></li>
                                <li><a href="CambioContrasena.jsp?contrasena=0&idUsuario=<%=datosUsu.get(0).getIdUsuario()%>">Cambiar Contraseña</a></li>
                                <li><a href="MenuUsuario?accion=cerrarSesion">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </form>   
        </div>
    </body>
</html>
<%
    }
%>