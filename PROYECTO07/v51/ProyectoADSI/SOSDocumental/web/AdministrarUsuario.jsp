<%-- 
    Document   : AdministrarUsuario
    Created on : 21-may-2019, 20:30:39
    Author     : APRENDIZ
--%>
<%@include file="MenuUsuario.jsp" %>
<%    //HttpSession objSesion = request.getSession();
    //Recibe los datos que consulta
    ArrayList<Usuario> listar = (ArrayList) request.getAttribute("listarUsu");
    ArrayList<Usuario> modificar = (ArrayList) request.getAttribute("modificarUsu");
    String registrar = (String) request.getAttribute("registrarUsu");

    ArrayList<Rol> listarRol = (ArrayList) request.getAttribute("datosRolUsu");
    //ArrayList<ArrayList<ArrayList>> listarPerMod = (ArrayList) objSesion.getAttribute("listarPerMod");
    //ArrayList<ArrayList> listarPerRol = (ArrayList) objSesion.getAttribute("listarPerRol");
    //String modulo = (String) objSesion.getAttribute("modulo");
    //String moduloSelect = (String) objSesion.getAttribute("moduloSelect");

    String error = (String) request.getAttribute("errorUsu");//Se estan llamando de los otros jsp
    String ok = (String) request.getAttribute("okUsu");//Se estan llamando de los otros jsp

    if (permisos != null && permisos.get(0).equals("0")) {
        //System.out.println(modificar.size());
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
        <link href="css/PermisosRol.css" rel="stylesheet">
        <link href="css/EncabezadoBusqueda.css" rel="stylesheet">
        <title>Administrar usuario</title>
    </head>
    <body>
        <header>
            <h1>Administrar Usuarios</h1>
        </header>
        <main>
            <form action="Usuario" method="get">
                <input type="hidden" name="modulo" value="<%=modulo%>">
                <div id="listar">
                    <div id="buscarNuevo">
                        <label for="idNomUsuario">Identificacion o nombre del usuario</label>
                        <input type="search" id="idNomUsuario" name="idNomUsuario" placeholder="Cédula o nombre">
                        <input type="submit" value="Consultar" name="accion" formnovalidate>
                        <%
                            //Permisos para Crear
                            if (permisos.get(1).equals("0")) {
                        %>
                        <input type="submit" value="Nuevo Usuario" name="accion" formnovalidate>
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
                                <th>ID Usuario</th>
                                <th>Primer Nombre</th>
                                <th>Segundo Nombre</th>
                                <th>Primer Apellido</th>
                                <th>Segundo Apellido</th>
                                <th>Correo</th>
                                <th>idRol</th>
                                <th>Estado</th>
                                <th>Seleccionar</th>
                            </tr>
                            <%
                                for (int i = 0; i < listar.size(); i++) {
                            %>
                            <tr>
                                <td><label for="<%=i%>"><%=listar.get(i).getIdUsuario()%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getPrimNombre()%></label></td>
                                <td><label for="<%=i%>"><%if (listar.get(i).getSegNombre() != null) {%><%=listar.get(i).getSegNombre()%><%}%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getPrimApellido()%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getSegApellido()%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getCorreo()%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getIdRol()%></label></td>
                                <td><label for="<%=i%>"><%=listar.get(i).getEstadoUsuario()%></label></td>
                                <td><label for="<%=i%>"><input type="radio" id="<%=i%>" value="<%=listar.get(i).getIdUsuario()%>" name="idUsuarioSelect" <%if (modificar == null && registrar == null) {%>required<%}%>></label></td> 
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
                                Modificar Usuario
                                <%
                                } else {
                                %>
                                Nuevo Usuario
                                <%
                                    }
                                %>

                            </h1>

                            <label for="idUsuario">Id Usuario</label>
                            <input type="number" id="idUsuario" name="idUsuario"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdUsuario()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <input type="hidden" name="idUsuarioSelect"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdUsuario()%>"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="primNombre">Primer nombre</label>
                            <input type="text" id="primNombre" name="primNombre" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getPrimNombre()%>"
                                   <%
                                       }
                                   %>
                                   required>
                            <label for="segNombre">Segundo nombre</label>
                            <input type="text" id="segNombre" name="segNombre" 
                                   <%
                                       if (modificar != null && modificar.get(0).getSegNombre() != null) {
                                   %>
                                   value="<%=modificar.get(0).getSegNombre()%>"
                                   <%
                                       }
                                   %>
                                   >
                            <label for="primApellido">Primer apellido</label>
                            <input type="text" id="primApellido" name="primApellido" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getPrimApellido()%>"
                                   <%
                                       }
                                   %>
                                   required>
                            <label for="segApellido">Segundo apellido</label>
                            <input type="text" id="segApellido" name="segApellido" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getSegApellido()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="correo">Correo</label>
                            <input type="email" id="correo" name="correo" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getCorreo()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="idRol">Seleccionar Rol</label>
                            <select name="idRol" id="idRol" required="">
                                <option value="">Seleccionar</option>
                                
                                <%
                                    if (listarRol != null) {
                                        for (int i = 0; i < listarRol.size(); i++) {

                                            if ((modificar != null) && listarRol.get(i).getIdRol().equals(modificar.get(0).getIdRol())) {
                                %>
                                <option value="<%=listarRol.get(i).getIdRol()%>"selected><%=listarRol.get(i).getNomRol()%></option>
                                <%
                                } else {
                                %>
                                <option value="<%=listarRol.get(i).getIdRol()%>"><%=listarRol.get(i).getNomRol()%></option>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </select>

                            <label for="estadoUsuario">Estado Usuario</label>
                            <select name="estadoUsuario" id="estadoUsuario">
                                <option value="Activo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoUsuario().equals("Activo")) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Activo</option>

                                <option value="Inactivo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoUsuario().equals("Inactivo")) {
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
                            <input type="submit" value="Registrar" name="accion">
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
                %>
                 <label><%if (error != null) {%><%=error%><%}%></label>
                <label><%if (ok != null) {%><%=ok%><%}%></label>
           
            </form>
        </main>
    </body>
</html>
<%}%>

