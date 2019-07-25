<%-- 
    Document   : AdministrarPerfil
    Created on : 13/05/2019, 06:42:36 PM
    Author     : Einer
--%>
<%@include file="MenuUsuario.jsp" %>

<%    //Recibe los datos que consulta
    ArrayList<Perfil> listar = (ArrayList) request.getAttribute("listarPer");
    // String registrar = (String) objSesion.getAttribute("registrar");

    //Obtenemos los permisos del servlet Admistrar perfil
    ArrayList<Modulo> listarModulos = (ArrayList) request.getAttribute("listarModulosPer");
    ArrayList<Perfil> modificar = (ArrayList) request.getAttribute("modificarPer");
    String registrar = (String) request.getAttribute("registrarPer");//Se estan llamando de los otros jsp
    String error = (String) request.getAttribute("errorPer");//Se estan llamando de los otros jsp
    String ok = (String) request.getAttribute("okPer");//Se estan llamando de los otros jsp

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
        <!--<meta name="viewport" content="width=device-width, user scalable=no, initial-scale=1.0, minimum-scale=1.0">-->
        <title>Admistrar Perfil</title>
    </head>
    <body>
        <header>
            <h1>Administrar Perfiles</h1>
        </header>
        <main>
            <form action="AdministrarPerfil" method="get">
                <input type="hidden" name="modulo" value="<%=modulo%>">
                <div id="listar">
                    <div id="buscarNuevo">
                        <label for="idNomPerfil">Código o nombre del perfil:</label>
                        <input type="search" id="idNomPerfil" name="idNomPerfil" placeholder="Código o nombre">

                        <input type="submit" value="Consultar" name="accion" formnovalidate>
                        <%
                            //Permisos para Crear
                            if (permisos.get(1).equals("0")) {
                        %>
                        <input type="submit" value="Nuevo Perfil" name="accion" formnovalidate>
                        <%
                            }
                        %>
                    </div>
                    <%
                        if (listar != null) {
                    %>
                    <div id="resultado">
                        <table>
                            <tr>
                                <th>ID Perfil</th>
                                <th>Código Perfil</th>
                                <th>Nombre Perfil</th>
                                <th>Descripción Perfil</th>
                                <th>Modulo</th>
                                <th>Estado Perfil</th>
                                <th>Seleccionar</th>
                            </tr>
                            <%
                                for (int i = 0; i < listar.size(); i++) {
                            %>
                            <tr>
                                <td> <%=listar.get(i).getIdPerfil()%></td>
                                <td> <%=listar.get(i).getCodigoPerfil()%></td>
                                <td> <%=listar.get(i).getNomPerfil()%></td>
                                <td> <%=listar.get(i).getDescripcionPerfil()%></td>
                                <td> <%=listar.get(i).getNomModulo()%></td>
                                <td> <%=listar.get(i).getEstadoPerfil()%></td>
                                <td><input type="radio" value="<%=listar.get(i).getIdPerfil()%>" name="idPerfil" id="idPerfil" <%if (modificar == null && registrar == null) {%>required<%}%>> </td> 
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>
                <%
                    //Permisos para modificar
                    if (permisos.get(2).equals("0")) {
                %>
                <div id="btn">
                    <input type="submit" value="Modificar" name="accion">
                </div>
                <%
                        }
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
                                Modificar Perfil
                                <%
                                } else {
                                %>
                                Nuevo Perfil
                                <%
                                    }
                                %>

                            </h1>

                            <label for="PerfilId">Id Perfil</label>
                            <input type="text" id="PerfilId" name="PerfilId"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdPerfil()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%=registrar%>"
                                   <%
                                       }
                                   %>
                                   required disabled>

                            <input type="hidden" id="idPerfil" name="idPerfil"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdPerfil()%>"
                                   <%
                                   } else {
                                   %>
                                   value="<%=registrar%>"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="PerfilCodigo">Código Perfil</label>
                            <input type="text" id="PerfilCodigo" name="PerfilCodigo"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getCodigoPerfil()%>"
                                   <%
                                   } else {
                                   %>
                                   value="PER-"
                                   <%
                                       }
                                   %>
                                   required disabled>

                            <input type="hidden" id="codigoRol" name="codigoPerfil"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getCodigoPerfil()%>"
                                   <%
                                   } else {
                                   %>
                                   value="PER-"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="nomPerfil">Nombre Perfil</label>
                            <input type="text" id="nomPerfil" name="nomPerfil" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getNomPerfil()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="desPerfil">Descripción Perfil</label>
                            <textarea id="nomPerfil" name="desPerfil"required><%if (modificar != null) {
                                    out.println(modificar.get(0).getDescripcionPerfil());
                                }%></textarea>

                            <label for="idModulo">Modulo</label>
                            <select name="idModulo" id="idModulo" required="">
                                <option value="">Seleccionar</option>
                                <%
                                    if (listarModulos != null) {
                                        for (int i = 0; i < listarModulos.size(); i++) {
                                            if ((modificar != null) && listarModulos.get(i).getIdModulo().equals(modificar.get(0).getIdModulo())) {
                                %>
                                <option value="<%=listarModulos.get(i).getIdModulo()%>"selected><%=listarModulos.get(i).getNomModulo()%></option>
                                <%//Para que no muestre el mosulo cambiar contraseña
                                } else if(!listarModulos.get(i).getNomModulo().startsWith("Cambiar Contrasena")){
                                %>
                                <option value="<%=listarModulos.get(i).getIdModulo()%>"><%=listarModulos.get(i).getNomModulo()%></option>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </select>

                            <label for="tipoPer">Tipo de perfil</label>
                            <select name="tipoPer" id="tipoPer" required="">
                                <option value="">Seleccionar</option>
                                <option value="PER-CRE-"
                                        <%
                                            if (modificar != null && (modificar.get(0).getCodigoPerfil()).equals("PER-CRE-" + modificar.get(0).getIdModulo())) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Perfil para Crear</option>
                                <option value="PER-LIS-"
                                        <%
                                            if (modificar != null && (modificar.get(0).getCodigoPerfil()).equals("PER-LIS-" + modificar.get(0).getIdModulo())) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Perfil para Listar</option>
                                <option value="PER-MOD-"
                                        <%
                                            if (modificar != null && (modificar.get(0).getCodigoPerfil()).equals("PER-MOD-" + modificar.get(0).getIdModulo())) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Perfil para Modificar</option>
                            </select>

                            <label for="estadoPerfil">Estado Perfil</label>
                            <select name="estadoPerfil" id="estadoPerfil" required="">
                                <option value="">Seleccionar</option>
                                <option value="Activo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoPerfil().equals("Activo")) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Activo</option>

                                <option value="Inactivo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoPerfil().equals("Inactivo")) {
                                        %>
                                        selected
                                        <%
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
                            <input type="submit" value="Guardar" name="accion" >
                            <%
                                }
                            %>
                            <input type="submit" value="Cancelar" name="accion" formnovalidate>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <div>
                    <%=error%>
                </div>
            </form>
        </main>

    </body>
</html>

<%}%>
