<%-- 
    Document   : OpcionesMenu
    Created on : 15-may-2019, 20:57:58
    Author     : Einer
--%>
<%@include file="MenuUsuario.jsp" %>

<%
    String idCla = (String) request.getParameter("idCla"); //Obtenermos los datos del objeto  
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MenuUsuario" method="post">
            <h1>Seleccionar modulo</h1>
            <%                
                if ((datosMod != null && idCla != null) && (datosMod.size() > 0 && datosClaMod != null)) {

                    String estado = "";
                    int cont = 0;

                    for (int i = 0; i < datosClaMod.size(); i++) {
                        if (datosClaMod.get(i).getIdClasificacionModulo().equals(idCla)) {
                            estado = datosClaMod.get(i).getEstadoClasificacionModulo();
                        }
                    }
                    for (int i = 0; i < datosMod.size(); i++) {
                        if ((datosMod.get(i).get(0).getIdClasificacionModulo().equals(idCla) && datosMod.get(i).get(0).getEstadoModulo().equals("Activo")) && estado.equals("Activo")) {
                            cont = 1;

            %>
            <a href="MenuUsuario?accion=cargarModulo&idMod=<%=datosMod.get(i).get(0).getIdModulo()%>"><%=datosMod.get(i).get(0).getNomModulo()%></a><br>
            <%
            } else {
                //Cuando el modulo esta inactivo lo bloque en las opciones
                if (datosMod.get(i).get(0).getIdClasificacionModulo().equals(idCla)) {
                    cont = 1;
            %>
            <label><%=datosMod.get(i).get(0).getNomModulo()%>: Desactivado</label><br>
            <%
                        }
                    }
                }
                if (cont < 1) {
            %>
            <h2>Esta sesión No contiene modulos</h2>
            <%
                }
            } else {
            %>
            <h2>Esta sesión no contiene modulos</h2>
            <%
                }
            %>
        </form>
    </body>
</html>
