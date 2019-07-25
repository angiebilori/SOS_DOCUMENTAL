package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Actualizacion;
import model.Descarga;
import model.Documento;
import model.Fecha;
import model.Modulo;
import model.ReporteEspecifico;
import model.ReporteGlobal;
import model.dao.DAOActualizacion;
import model.dao.DAODescarga;
import model.dao.DAODocumento;
import model.dao.DAOModulo;
import model.dao.DAOReporteEspecifico;
import model.dao.DAOReporteGlobal;

/**
 *
 * @author Einer
 */
@WebServlet(name = "agregarPdfServlet", urlPatterns = {"/agregarPdf"})
@MultipartConfig(maxFileSize = 112000000)    // upload file's size up to 16MB = 16177215

public class AdministrarDocumentoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Documento doc = new Documento();
    DAODocumento dDoc = new DAODocumento();

    Actualizacion act = new Actualizacion();
    DAOActualizacion dAct = new DAOActualizacion();

    ReporteGlobal reGlob = new ReporteGlobal();
    DAOReporteGlobal dReGlob = new DAOReporteGlobal();

    ReporteEspecifico reEsp = new ReporteEspecifico();
    DAOReporteEspecifico dReEsp = new DAOReporteEspecifico();

    Modulo mod = new Modulo();
    DAOModulo dMod = new DAOModulo();

    Descarga des = new Descarga();
    DAODescarga dDes = new DAODescarga();

    Fecha fec = new Fecha();

    //Variables
    private String idNomDocumento;
    private String idDocumento;
    private String nomDocumento;
    private String fecha;
    private String idUsuario;
    private Part filePart; //Se obtiene el Documento
    private String versionDocumento;
    private String tipoDocumento;
    private String estadoDocumento;
    private String descripcionDocumento;

    private InputStream documento = null;
    private String idDocumentoSelect;
    private String accion;
    private String ok;
    private String error;
    private String linkRedireccionar;
    private String opcionDescarga;

    private ArrayList<Documento> listar;
    private ArrayList<Actualizacion> listarA;
    ArrayList<Documento> modificar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        idNomDocumento = request.getParameter("idNomDocumento");
        idDocumento = request.getParameter("idDocumento");
        nomDocumento = request.getParameter("nomDocumento");
        idUsuario = request.getParameter("idUsuario");
        filePart = request.getPart("documento"); //Se obtiene el Documento
        versionDocumento = request.getParameter("versionDocumento");
        tipoDocumento = request.getParameter("tipoDocumento");
        estadoDocumento = request.getParameter("estadoDocumento");
        descripcionDocumento = request.getParameter("desDocumento");
        idDocumentoSelect = request.getParameter("idDocumentoSelect");
        accion = request.getParameter("accion");
        opcionDescarga = request.getParameter("opcionDescarga");

        //Se obtiene la Fecha actual
        fecha = fec.obtenerFecha();

        //Asignacion de valores
        doc.setIdDocumento(idDocumento);
        doc.setNomDocumento(nomDocumento);
        doc.setFechaRegistroDocumento(fecha);
        doc.setIdUsuario(idUsuario);
        doc.setDocumento(documento);
        doc.setVersionDocumento(versionDocumento);
        doc.setTipoDocumento(tipoDocumento);
        doc.setEstadoDocumento(estadoDocumento);
        doc.setDescripcionDocumento(descripcionDocumento);

        //mod.setIdModulo("4"); //asignamos dos del id del modulo de AdministracionPerfil
        linkRedireccionar = request.getParameter("modulo");//El link del modulo se encuentra en la sesion

        actualizarAuto();
        error = "";
        ok = "";
        try {
            switch (accion) {

                case "Nuevo Documento":
                    request.setAttribute("registrarDoc", "1");
                    redireccionar(request, response);
                    break;

                case "Agregar":
                    if (registrarDocumento()) {
                        request.setAttribute("okDoc", ok);
                    } else {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Consultar":
                    if (consultarDocumento()) {
                        request.setAttribute("listarDoc", listar);
                    } else {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Detalle":
                    if (detallesDocumento()) {
                        request.setAttribute("listarDetallesDoc", listar);
                        request.setAttribute("listarDetallesADoc", listarA);
                    } else {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Modificar":
                    if (datosModificar()) {
                        request.setAttribute("modificarDoc", listar);
                    } else {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Guardar":
                    if (guardarModificacion()) {
                        request.setAttribute("okDoc", ok);
                    } else {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Descargar":
                    descargarDocumento(response);
                    if (error != null) {
                        request.setAttribute("errorDoc", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Cancelar":
                    redireccionar(request, response);
                    break;
            }

        } catch (IOException | ServletException | NullPointerException e) {
            response.sendRedirect("MenuUsuario?accion=cerrarSesion"); //Si es verdadero nos envia a el menu jsp
        }
    }

    //Metodos para registrar consultar etc
    protected boolean registrarDocumento() {
        try {
            if (filePart.getSize() > 0) {
                documento = filePart.getInputStream();
                doc.setDocumento(documento);
            } else {
                error = "No se ha agregado un archivo";
            }
            if (documento != null) {
                if (dDoc.listarDocumentoId(doc) != null) {
                    error = "El documento ya existe";
                } else {
                    if (dDoc.registroDocumento(doc)) {

                        //Datos Que guardará las tablas de reporte especifico y reporte global
                        reGlob.setIdDocumento(idDocumento);
                        reGlob.setFechaReporteGlobal(fecha);

                        reEsp.setIdDocumento(idDocumento);
                        reEsp.setFechaRegistroDocumento(fecha);
                        reEsp.setIdUsuario(idUsuario);
                        reEsp.setVersionDocumento(versionDocumento);

                        if ((dReGlob.registroRG(reGlob)) && (dReEsp.registroRE(reEsp))) {
                            ok = "Documento se registró";
                            documento = null;
                            return true;
                        } else {
                            ok = "Se guardo el documento, NO se guardo Rporte GLobal o Reporte Expecifico";
                            return true;
                        }
                    } else {
                        error = "No se guardó documento";
                    }
                }
            } else {
                error = "No se adjunto documento";
            }
        } catch (IOException e) {

        }
        return false;
    }

    protected boolean consultarDocumento() {
        doc.setIdDocumento(idNomDocumento);
        doc.setNomDocumento(idNomDocumento);
        listar = dDoc.listarDocumentoIdNom(doc);
        if (listar != null) {
            return true;
        } else {
            error = "No se encontraron resultados";
        }
        return false;
    }

    protected boolean detallesDocumento() {
        doc.setIdDocumento(idDocumentoSelect);
        act.setIdDocumento(idDocumentoSelect);

        listar = dDoc.listarDocumentoId(doc);
        listarA = dAct.listarActualizacionId(act);
        if (listar != null) {
            reGlob.setIdDocumento(idDocumentoSelect);
            actualizarReGloBusqueda();
            ok = "Registros encontrados";
            return true;
        } else {
            error = "No se encontraron registros";
        }
        return false;
    }

    protected boolean datosModificar() {

        doc.setIdDocumento(idDocumentoSelect);
        modificar = dDoc.listarDocumentoId(doc);
        if (modificar != null) {
            return true;
        } else {
            error = "No se obtuvieron los datos a modificar";
        }
        return false;
    }

    protected boolean guardarModificacion() {
        //Existencia de Documento
        try {
            if (dDoc.listarDocumentoId(doc) != null) {
                if (filePart.getSize() > 0) {
                    System.out.println("ASs");
                    documento = filePart.getInputStream();
                } else {
                    error = "No se adjunto documento";
                }
                //Si no se agrega documento es porque solo se actualizará la informacion
                if (documento == null) {
                    if (dDoc.actualizarDocumentoInfo(doc, idDocumentoSelect)) {
                        ok = "Se actualizó la información del documento";
                        return true;
                    } else {
                        error = "No se actualizó la información del documento";
                    }
                } else {
                    act.setIdDocumento(idDocumentoSelect);
                    act.setFechaRegistroDocumento(fecha);
                    act.setIdUsuario(idUsuario);
                    act.setDocumento(documento);
                    act.setTipoDocumento(tipoDocumento);
                    act.setDescripcionDocumento(descripcionDocumento);

                    ArrayList<Actualizacion> actt;

                    actt = dAct.listarActualizacionId(act);
                    if (actt != null) {
                        versionDocumento = Integer.toString(Integer.parseInt(actt.get(0).getVersionDocumento()) + 1);
                        doc.setFechaRegistroDocumento(actt.get(0).getFechaRegistroDocumento());
                        doc.setIdUsuario(actt.get(0).getIdUsuario());
                        doc.setDocumento((actt.get(0).getDocumento()));
                        doc.setVersionDocumento(actt.get(0).getVersionDocumento());
                        doc.setTipoDocumento(actt.get(0).getTipoDocumento());
                        doc.setDescripcionDocumento(actt.get(0).getDescripcionDocumento());

                        act.setVersionDocumento(versionDocumento);
                        if (dDoc.actualizarDocumento(doc, idDocumentoSelect) && dAct.actulizarActualizacion(act)) {

                            //Datos Que guardará las tablas de reporte especifico y reporte global
                            reEsp.setIdDocumento(idDocumento);
                            reEsp.setFechaRegistroDocumento(fecha);
                            reEsp.setIdUsuario(idUsuario);
                            reEsp.setVersionDocumento(versionDocumento);

                            if (dReEsp.registroRE(reEsp)) {
                                ok = "Documento actualizado";
                                return true;
                            } else {
                                ok = "Se guardo el documento, NO se guardo Reporte GLobal o Reporte Expecifico";
                                return true;
                            }
                        } else {
                            error = "Documento no actualizado";
                        }
                    } else {
                        //doc.setIdDocumento(idDocumentoSelect);
                        versionDocumento = Integer.toString(Integer.parseInt(dDoc.listarDocumentoId(doc).get(0).getVersionDocumento()) + 1);
                        act.setVersionDocumento(versionDocumento);
                        if (dAct.registroActualizacion(act)) {
                            //Datos Que guardará las tablas de reporte especifico y reporte global
                            reEsp.setIdDocumento(idDocumento);
                            reEsp.setFechaRegistroDocumento(fecha);
                            reEsp.setIdUsuario(idUsuario);
                            reEsp.setVersionDocumento(versionDocumento);

                            if (dReEsp.registroRE(reEsp)) {
                                ok = "Documento actualizado";
                                return true;
                            } else {
                                ok = "Se guardo el documento, NO se guardo Reporte GLobal o Reporte Expecifico";
                                return true;
                            }
                        } else {
                            error = "No se registro el documento";
                        }
                    }
                }
            } else {
                error = "No se encontro el documento a actualizar";
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            error = e.toString();
        }
        return false;
    }

    //Actualiza el si ya lleva un mes en actualización
    protected void actualizarAuto() {

        ArrayList<Actualizacion> actt;

        act.setFechaRegistroDocumento(fec.restarMes());
        actt = dAct.listarActualizacionFecha(act);

        if (actt != null) {

            for (int i = 0; i < actt.size(); i++) {

                versionDocumento = Integer.toString(Integer.parseInt(actt.get(i).getVersionDocumento()) + 1);
                doc.setIdDocumento(actt.get(i).getIdDocumento());
                doc.setFechaRegistroDocumento(actt.get(i).getFechaRegistroDocumento());
                doc.setNomDocumento(actt.get(i).getNomDocumento());
                doc.setIdUsuario(actt.get(i).getIdUsuario());
                doc.setDocumento((actt.get(i).getDocumento()));
                doc.setVersionDocumento(actt.get(i).getVersionDocumento());
                doc.setTipoDocumento(actt.get(i).getTipoDocumento());
                doc.setEstadoDocumento(actt.get(i).getEstadoDocumento());
                doc.setDescripcionDocumento(actt.get(i).getDescripcionDocumento());

                act.setIdDocumento(actt.get(i).getIdDocumento());
                if (dDoc.actualizarDocumento(doc, actt.get(i).getIdDocumento()) && dAct.eliminarActualizacion(act)) {
                    ok = "Documento actualizado";
                } else {
                    error = "Documento no actualizado";
                }
            }
        }
    }

    protected void descargarDocumento(HttpServletResponse response) {

        doc.setIdDocumento(idDocumentoSelect);
        if (opcionDescarga.equals("documento")) {
            listar = dDoc.listarDocumentoId(doc);
            if (listar != null) {

                descargar(response, listar.get(0).getDocumento(), listar.get(0).getVersionDocumento(), listar.get(0).getTipoDocumento());
            } else {
                error = "Error al descargar";
            }
        } else {
            act.setIdDocumento(idDocumentoSelect);
            listarA = dAct.listarActualizacionId(act);
            if (listarA != null) {
                descargar(response, listarA.get(0).getDocumento(), listarA.get(0).getVersionDocumento(), listarA.get(0).getTipoDocumento());
            } else {
                error = "Error al descargar";
            }
        }
    }

    public void registrarDescarga() {
        des.setFechaDescarga(fecha);
        des.setIdDocumento(listar.get(0).getIdDocumento());
        des.setIdUsuario(idUsuario);
        dDes.registrarDescarga(des);
    }

    public void actualizarReGloBusqueda() {
        //Se actualiza la tabla reporte global descargas
        ArrayList<ReporteGlobal> reGlo = dReGlob.listarReporteGlobal(reGlob);
        if (reGlo != null) {
            reGlob.setCantidadBusquedas(Integer.toString(Integer.parseInt(reGlo.get(0).getCantidadBusquedas()) + 1));
            reGlob.setIdDocumento(reGlo.get(0).getIdDocumento());
            dReGlob.actualizarBusquedasRG(reGlob);
        } else {
            error = "Error al actualizar el reporte global";
        }
    }

    public void actualizarReGloDescarga() {
        //Se actualiza la tabla reporte global descargas
        ArrayList<ReporteGlobal> reGlo = dReGlob.listarReporteGlobal(reGlob);
        if (reGlo != null) {
            System.out.println(reGlo.get(0).getCantidadDescargas() + "Des");
            reGlob.setCantidadDescargas(Integer.toString(Integer.parseInt(reGlo.get(0).getCantidadDescargas()) + 1));
            reGlob.setIdDocumento(reGlo.get(0).getIdDocumento());
            if (!dReGlob.actualizarDescargasRG(reGlob)) {
                error = "Error al registar la descarga";
            }
        } else {
            error = "Error al actualizar el reporte global";
        }
    }

    private void descargar(HttpServletResponse response, InputStream documento, String version, String tipoDocumento) {

        try (InputStream descarga = documento) {
            //InputStream descargar = new ByteArrayInputStream(documentoA); //Docuemnto en tipo bytes[]
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachement; filename = " + listar.get(0).getIdDocumento() + "-" + listar.get(0).getNomDocumento() + "-V" + version + tipoDocumento);
            response.setContentLength(descarga.available());
            int tamanoInput = descarga.available();
            byte[] datosPDF = new byte[tamanoInput];
            descarga.read(datosPDF, 0, tamanoInput);

            try (ServletOutputStream oot = response.getOutputStream()) {
                oot.flush();
                oot.write(datosPDF);
                registrarDescarga(); //Se registra la descarga

                des.setIdDocumento(listar.get(0).getIdDocumento());
                actualizarReGloDescarga();
                //response.getOutputStream().write(datosPDF);
            }
        } catch (Exception e) {
            System.out.println("error descargar " + e);
        }
    }

    protected void redireccionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(linkRedireccionar).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
