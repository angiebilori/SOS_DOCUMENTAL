<%-- 
    Document   : AdministrarDocumento
    Created on : 21/05/2019, 02:24:07 PM
    Author     : Einer
--%>
<%@include file="MenuUsuario.jsp" %>

<%    //HttpSession objSesion = request.getSession();
    ArrayList<Documento> listar = (ArrayList) request.getAttribute("listarDoc");
    ArrayList<Documento> listarDetalles = (ArrayList) request.getAttribute("listarDetallesDoc");
    ArrayList<Documento> listarDetallesA = (ArrayList) request.getAttribute("listarDetallesADoc");
    
    ArrayList<Documento> modificar = (ArrayList) request.getAttribute("modificarDoc");
    String registrar = (String) request.getAttribute("registrarDoc");//Se estan llamando de los otros jsp
    String error = (String) request.getAttribute("errorDoc");//Se estan llamando de los otros jsp
    String ok = (String) request.getAttribute("okDoc");//Se estan llamando de los otros jsp

    //Verifica que el modulo este activo
    if (permisos != null && permisos.get(0).equals("0")) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/cargarArchivo.js"></script>
        <link href="css/Btns.css" rel="stylesheet">
        <link href="css/Titulos.css" rel="stylesheet">
        <link href="css/EncabezadoBusqueda.css" rel="stylesheet">
        <link href="css/Tablaresultados.css" rel="stylesheet">
        <link href="css/previsualizar.css" rel="stylesheet">
        <title>Administrar Documentos</title>
    </head>
    <body>
        <header>
            <h1>Administrar Documentos</h1>
        </header>
        <main>
            <form action="AdministrarDocumento" method="post" enctype="multipart/form-data">
                <input type="hidden" name="modulo" value="<%=modulo%>">
                <input type="hidden"name="idUsuario" value="<%=datosUsu.get(0).getIdUsuario()%>">
                <div id="listar">
                    <div>
                        <label for="idNomDocumento">Código o nombre del documento</label>
                        <input type="search" id="idNomDocumento" name="idNomDocumento" placeholder="Código o nombre">

                        <input type="submit" value="Consultar" name="accion" formnovalidate>
                        <%                            //Permisos para Crear
                            if (permisos.get(1).equals("0")) {
                        %>
                        <input type="submit" value="Nuevo Documento" name="accion" formnovalidate>
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
                                <th>ID Documento</th>
                                <th>Nombre Documento</th>
                                <th>Fecha Documento</th>
                                <th>Descripción Documento</th>
                                <th>Seleccionar</th>
                            </tr>
                            <%
                                for (int i = 0; i < listar.size(); i++) {
                            %>
                            <tr>
                                <td> <%=listar.get(i).getIdDocumento()%></td>
                                <td> <%=listar.get(i).getNomDocumento()%></td>
                                <td> <%=listar.get(i).getFechaRegistroDocumento()%></td>
                                <td> <%=listar.get(i).getDescripcionDocumento()%></td>
                                <td><input type="radio" value="<%=listar.get(i).getIdDocumento()%>" name="idDocumentoSelect" <%if ((modificar == null && registrar == null) && (listarDetalles == null)) {%>required<%}%>> </td> 
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                    <div id="btn">
                        <input type="submit" value="Detalle" name="accion">
                    </div>
                    <%
                        }
                        if (listarDetalles != null) {
                    %>
                    <div id="resultado">
                        <table>
                            <tr>
                                <th>ID Documento</th>
                                <th>Nombre Documento</th>
                                <th>Fecha Documento</th>
                                <th>Usuario-Registró</th>
                                <th>Versión Documento</th>
                                <th>Tipo Documento</th>
                                <th>Estado Documento</th>
                                <th>Descripción Documento</th>
                                <th>Seleccionar</th>
                            </tr>
                            <%
                                for (int i = 0; i < listarDetalles.size(); i++) {
                            %>
                            <tr>
                                <td> <%=listarDetalles.get(i).getIdDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getNomDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getFechaRegistroDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getIdUsuario()%></td>
                                <td> <%=listarDetalles.get(i).getVersionDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getTipoDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getEstadoDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getDescripcionDocumento()%></td>
                            <input type="hidden" name="idDocumentoSelect" value="<%=listarDetalles.get(i).getIdDocumento()%>">
                            <td><input type="radio" checked value="documento" name="opcionDescarga" <%if ((modificar == null && registrar == null)) {%>required<%}%>> </td> 
                            </tr>
                            <%
                                if (listarDetallesA != null) {
                            %>
                            <tr>
                                <th></th>
                            </tr>
                            <tr>
                                <th colspan="9">Actualmente cuenta con una actualización</th>
                            </tr>
                            <tr>
                                <td> <%=listarDetallesA.get(i).getIdDocumento()%></td>
                                <td> <%=listarDetallesA.get(i).getNomDocumento()%></td>
                                <td> <%=listarDetallesA.get(i).getFechaRegistroDocumento()%></td>
                                <td> <%=listarDetallesA.get(i).getIdUsuario()%></td>
                                <td> <%=listarDetallesA.get(i).getVersionDocumento()%></td>
                                <td> <%=listarDetallesA.get(i).getTipoDocumento()%></td>
                                <td> <%=listarDetalles.get(i).getEstadoDocumento()%></td>
                                <td> <%=listarDetallesA.get(i).getDescripcionDocumento()%></td>
                            <input type="hidden" name="idDocumentoSelect"  value="<%=listarDetallesA.get(i).getIdDocumento()%>">
                            <td><input type="radio" value="actualizacion" name="opcionDescarga" <%if ((modificar == null && registrar == null)) {%>required<%}%>> </td> 
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </div>
                    <%  //Permisos para modificar
                        if (permisos.get(2).equals("0")) {
                    %>
                    <div id="btn">
                        <input type="submit" value="Modificar" name="accion">
                        <input type="submit" value="Descargar" name="accion">
                    </div>
                    <%
                            }
                        }
                    %>
                    </table>
                </div>
                </div>
                <%
                    if (modificar != null || registrar != null) {
                %>

                <div id="modificar">
                    <div id="contenido">
                        <div id="inputs">

                            <h1>
                                <%
                                    if (modificar != null) {
                                %>
                                Modificar Documento
                                <%
                                } else {
                                %>
                                Nuevo Documento
                                <%
                                    }
                                %>

                            </h1>

                            <label for="idDocumento">Id Documento</label>
                            <input type="number" id="idDocumento" name="idDocumento"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdDocumento()%>"
                                   <%
                                       }
                                   %>
                                   required >
                            <input type="hidden"name="idDocumentoSelect"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getIdDocumento()%>"
                                   <%
                                       }
                                   %>
                                   >

                            <label for="nomDocumento">Nombre Documento</label>
                            <input type="text" id="nomDocumento" name="nomDocumento"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getNomDocumento()%>"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="files">Adjuntar</label>
                            <input id="files" type="file" onchange="processFiles(this.files)" name="documento" <%if (modificar == null) {%>required<%}%> placeholder="Anexar">


                            <label for="versionDocumento">Versión Documento</label>
                            <input type="text" id="versionDocumento" name="versionDocumento" 
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getVersionDocumento()%>"
                                   <%
                                   } else {
                                   %>
                                   value="1"
                                   <%
                                       }
                                   %>
                                   required disabled>

                            <input type="hidden" name="versionDocumento"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getVersionDocumento()%>"
                                   <%
                                   } else {
                                   %>
                                   value="1"
                                   <%
                                       }
                                   %>
                                   required>

                            <label for="tipoDocumento">Tipo documento</label>
                            <input type="text" id="documentoTipo"  name="tipoDocumento"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getTipoDocumento()%>"
                                   <%
                                       }
                                   %>
                                   required disabled>
                            <input type="hidden" id="tipoDocumento" name="tipoDocumento"
                                   <%
                                       if (modificar != null) {
                                   %>
                                   value="<%=modificar.get(0).getTipoDocumento()%>"
                                   <%
                                       }
                                   %>
                                   required>                                 
                                   <output id="list"></output>
                            <label for="estadoDocumento">Estado Documento</label>
                            <select name="estadoDocumento" id="estadoDocumento">
                                <option value="Activo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoDocumento().equals("Activo")) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Activo</option>

                                <option value="Inactivo"
                                        <%
                                            if (modificar != null && modificar.get(0).getEstadoDocumento().equals("Inactivo")) {
                                        %>
                                        selected
                                        <%
                                            }
                                        %>
                                        >Inactivo</option>
                            </select>

                            <label for="desDocumento">Descripción Documento</label>
                            <textarea id="desDocumento" name="desDocumento"required><%if (modificar != null) {
                                    out.println(modificar.get(0).getDescripcionDocumento());
                                }%></textarea>
                        </div>
                        <div class="previsualizar" id ="prev">
                            <div id="frame" class="prev2">
                        <iframe id="mm" src="" width="100%" height="100%"></iframe>

                        <!--<iframe id="mm" src="https://view.officeapps.live.com/op/embed.aspx?src=C:\Users\PARKA\Downloads.txt"></iframe>-->
                        <!--<embed id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                        <!--<object id="mm" src="" type="application/pdf" width="100%" height="100%"/>-->
                   <!-- </div>
                    <div id="bloquearpdf">
                    </div>--->
                </div>
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
                <%
                    }
                %>
                
                <div>
                    <label><%if (error != null) {%><%=error%><%}%></label>
                    <label><%if (ok != null) {%><%=ok%><%}%></label>
                </div>
            </form>
        </main>

    </body>
</html>
<%
    }
%>
