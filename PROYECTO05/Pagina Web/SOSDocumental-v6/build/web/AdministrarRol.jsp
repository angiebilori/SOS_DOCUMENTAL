<%-- 
    Document   : AdministrarRol
    Created on : 9/05/2019, 01:08:57 PM
    Author     : Einer
--%>
<%@include file="MenuUsuario.jsp" %>

<%    //Recibe los datos que consulta
    ArrayList<Rol> listar = (ArrayList) request.getAttribute("listarRol");

    //Datos para listar los perfiles del rol
    ArrayList<Modulo> listarModulos = (ArrayList) request.getAttribute("listarModulosRol");
    ArrayList<ArrayList<Perfil>> listarPerMod = (ArrayList) request.getAttribute("listarPerModRol");
    ArrayList<Perfil> listarPerRol = (ArrayList) request.getAttribute("listarPerRolRol");

    String nomRol = (String) request.getAttribute("nomRol");
    String idRolP = (String) request.getAttribute("idRol");

    ArrayList<Rol> modificar = (ArrayList) request.getAttribute("modificarRol");
    String registrar = (String) request.getAttribute("registrarRol");//Se estan llamando de los otros jsp
    String error = (String) request.getAttribute("errorRol");//Se estan llamando de los otros jsp
    String ok = (String) request.getAttribute("okRol");//Se estan llamando de los otros jsp

    if (permisos != null && permisos.get(0).equals("0")) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/ventanaModal.css" rel="stylesheet">
        <link href="css/Titulos.css" rel="stylesheet">
        <link href="css/Btns.css" rel="stylesheet">
        <link href="css/Tablaresultados.css" rel="stylesheet">
        <link href="css/PermisosRol.css" rel="stylesheet">
        <link href="css/EncabezadoBusqueda.css" rel="stylesheet">

        <title>Administrar Roles</title>
    </head>
    <body>
        <header>
            <h1>Administrar Roles</h1>
        </header>
        <main>
            <form action="AdministrarRol" method="get">
                <input type="hidden" name="modulo" value="<%=modulo%>">
                <div id="listar">
                    <div id="buscarNuevo">
                        <label for="idNomRol">Código o nombre del rol:</label>
                        <input type="search" id="idNomRol" name="idNomRol" placeholder="Código o nombre">

                        <input type="submit" value="Consultar" name="accion" formnovalidate>
                        <%
                            if (permisos.get(1).equals("0")) {
                        %>
                        <input type="submit" value="Nuevo Rol" name="accion" formnovalidate>
                        <%
                            }
                        %>
                    </div>
                    <%
                        if (listar != null) {
                    %>
                    <div id="resultado">
                        <table>
                            <tr id="tabla-titulos">
                                <th>ID Rol</th>
                                <th>Código Rol</th>
                                <th>Nombre Rol</th>
                                <th>Descripción Rol</th>
                                <th>Estado Rol</th>
                                <th>Seleccionar</th>
                            </tr>
                            <%
                                for (int i = 0; i < listar.size(); i++) {
                            %>
                                <tr>
                                    <td><label for="<%=i%>"><%=listar.get(i).getIdRol()%></label></td>
                                    <td><label for="<%=i%>"><%=listar.get(i).getCodigoRol()%></label></td>
                                    <td><label for="<%=i%>"><%=listar.get(i).getNomRol()%></label></td>
                                    <td><label for="<%=i%>"><%=listar.get(i).getDescripcionRol()%></label></td>
                                    <td><label for="<%=i%>"><%=listar.get(i).getEstadoRol()%></label></td>
                                    <td><label for="<%=i%>"><input type="radio" id="<%=i%>" value="<%=listar.get(i).getIdRol()%>" name="idRol" <%if (modificar == null && registrar == null) {%>required<%}%>></label></td> 
                                </tr>
                            <%
                                }
                            %>

                        </table>
                    </div>
                    <div id="btn">
                        <%
                            if (permisos.get(2).equals("0")) {
                        %>
                        <input type="submit" value="Modificar" name="accion">
                        <input type="submit" value="Permisos" name="accion">
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                    }
                    if (modificar != null || registrar != null) {
                %>
                <div id="modificar">
                    <div id="contenido">
                        <div id="inputs">
                            <h1>
                                <%
                                    if (modificar != null) {
                                %>
                                Modificar Rol
                                <%
                                } else {
                                %>
                                Nuevo Rol
                                <%
                                    }
                                %>

                            </h1>

                            <label for="rolId">Id Rol</label>
                            <input type="text" id="rolId" name="rolId"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdRol()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%=registrar%>"
                                   <%
                                       }
                                   %>
                                   required disabled>

                            <input type="hidden" id="idRol" name="idRol"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdRol()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%=registrar%>"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="codigoRol">Código Rol</label>
                            <input type="text" id="rolCodigo" name="rolCodigo"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getCodigoRol()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%="ROL-" + registrar%>"
                                   <%
                                       }
                                   %>
                                   required disabled>

                            <input type="hidden" id="codigoRol" name="codigoRol" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getCodigoRol()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%="ROL-" + registrar%>"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="nomRol">Nombre Rol</label>
                            <input type="text" id="nomRol" name="nomRol"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getNomRol()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="desRol">Descripción Rol</label>
                            <textarea id="desRol" name="desRol" required><%if (modificar != null) {
                                    out.println(modificar.get(0).getDescripcionRol());
                                }%></textarea>

                            <label for="estadoRol"> Estado Rol</label>
                            <select name="estadoRol" id="estadoRol">

                                <option value="Activo"
                                        <%
                                            if (modificar != null) {
                                                if (modificar.get(0).getEstadoRol().equals("Activo")) {
                                        %>
                                        selected
                                        <%
                                                }
                                            }
                                        %>
                                        >Activo</option>
                                <option value="Inactivo" 
                                        <%
                                            if (modificar != null) {
                                                if (modificar.get(0).getEstadoRol().equals("Inactivo")) {
                                        %> 
                                        selected
                                        <%
                                                }
                                            }
                                        %>
                                        >Inactivo</option>
                            </select>
                        </div>
                        <div id="btn">
                            <%
                                if (registrar != null) {
                            %>
                            <input type="submit" value="Agregar" name="accion">
                            <%
                            } else {
                            %>
                            <input type="submit" value="Guardar" name="accion">
                            <%
                                }
                            %>
                            <input type="submit" value="Cancelar" name="accion" formnovalidate>
                        </div>
                    </div>
                </div>

                <%
                    }
                    if (listarModulos != null) {
                %>
                <div id="permisos">
                    <div id="contenidoPermisos">
                        <div id="resultadoPermisos">
                            <table>
                                <tr>
                                    <th id="t1" colspan="2"><br><h2>Administar Permisos al rol: <%=nomRol%></h2><br></th>
                                </tr>
                                    <th id="info" colspan="2">RECUERDE: Para poder ver un modulo debe tener <br>seleccionado la casilla de listar: del modulo correspondiente.</th>
                                    <%
                                        int con = 0;
                                        for (int i = 0; i < listarModulos.size(); i++) {
                                            if (!listarModulos.get(i).getNomModulo().startsWith("Cambiar Contrasena")) {
                                    %>
                                <tr>
                                    <th id="t2" colspan="2"><%=listarModulos.get(i).getNomModulo()%></th>
                                </tr>
                                <%
                                    }
                                    if (listarPerMod != null && listarPerMod.get(i) != null) {
                                        for (int j = 0; j < listarPerMod.get(i).size(); j++) {
                                %>
                                <tr>
                                    <td><label for="<%=con%>"><%=listarPerMod.get(i).get(j).getNomPerfil()%></label></td>
                                    <%
                                        int cont = 0;
                                        if (listarPerRol != null) {
                                            for (int r = 0; r < listarPerRol.size(); r++) {
                                                if (listarPerMod.get(i).get(j).getIdPerfil().equals(listarPerRol.get(r).getIdPerfil())) {
                                                    cont = 1;
                                                }
                                            }
                                        }
                                        if (cont == 1) {
                                    %>
                                    <td id="check"><label for="<%=con%>"><input type="checkbox" id="<%=con%>" name="idPerfil" value="<%=listarPerMod.get(i).get(j).getIdPerfil()%>" checked/></label></td>
                                        <%
                                        } else {
                                        %>
                                    <td id="check"><label for="<%=con%>"> <input type="checkbox" id="<%=con%>" name="idPerfil" value="<%=listarPerMod.get(i).get(j).getIdPerfil()%>"/></label></td>
                                        <%
                                            }
                                        %>
                                </tr>
                                <%
                                    con++;
                                            }
                                        }
                                    }

                                %>
                            </table>
                        </div>
                        <div id="btn">
                            <input type="submit" value="Cancelar" name="accion" formnovalidate>
                            <input type="hidden" value="<%=idRolP%>" name="idRol">
                            <input type="submit" value="Cambiar" name="accion" formnovalidate>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <label><%if (error != null) {%><%=error%><%}%></label>
                <label><%if (ok != null) {%><%=ok%><%}%></label>
            </form>
        </main>
        <footer>
        </footer>
    </body>
</html>
<%
    }
%>
