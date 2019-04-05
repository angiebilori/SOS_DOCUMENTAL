/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.documento;
import model.fecha;

/**
 *
 * @author Einer
 */
@WebServlet(name = "agregarPdfServlet", urlPatterns = {"/agregarPdf"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class agregarPdfServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        //Se obtiene la sesion
        HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"

        //Se obtienen los datos del JSP
        String seleccion = request.getParameter("seleccion");
        String idDocumento = request.getParameter("idDocumento");
        String nomDocumento = request.getParameter("nomDocumento");
        String idUsuario = objsesion.getAttribute("idUsuario").toString();
        String tipoDocumento = request.getParameter("tipoDocumento");
        String cantDescargas = "0";
        String cantBusquedas = "0";
        String version = "1";
        String fecha = "";
        Part filePart = request.getPart("documento"); //Se obtiene el documento

        documento doc = new documento();
        fecha fec = new fecha();

        //Se obtiene la fecha actual
        if (fec.obtenerFecha()) {
            fecha = fec.getFecha();
        }

        InputStream documento = null;

        if (filePart.getSize() > 0) {
            documento = filePart.getInputStream();
        } else {
            objsesion.setAttribute("error", "No se adjunto documento");
            response.sendRedirect("anexos.jsp");
        }

        if (seleccion.equals("nuevoRegistro")) {

            doc.setIdDocumento(idDocumento);

            if (doc.consultaDocumento()) {

                objsesion.setAttribute("error", "El documento a ingresar ya existe");
                response.sendRedirect("anexos.jsp");

            } else {

                documento docc = new documento(idDocumento, nomDocumento, fecha, idUsuario, version, tipoDocumento, cantDescargas, cantBusquedas, documento);

                if (docc.registroDocumento()) {

                    if ((docc.registroReporteGlobal()) && (docc.registroReporteEspecifico())) {
                        objsesion.setAttribute("ok", "Se guardo el documento");
                        response.sendRedirect("anexos.jsp");
                    } else {
                        objsesion.setAttribute("ok", "Se guardo el documento, NO se guardo Rporte GLobal o Reporte Expecifico");
                        response.sendRedirect("anexos.jsp");
                    }
                } else {
                    objsesion.setAttribute("error", "No se guardó documento");
                    response.sendRedirect("anexos.jsp");
                }
            }
        }

        if (seleccion.equals("actualizar")) {

            doc.setIdDocumento(idDocumento);

            //Existencia de documento
            if (doc.consultaDocumento()) {

                //Si no hay documentos actualizandose
                if (doc.consultaActualizacion() == false) {

                    version = Integer.toString(Integer.parseInt(doc.getVersion()) + 1);

                    documento docc = new documento(idDocumento, nomDocumento, fecha, idUsuario, version, tipoDocumento, "", "", documento);

                    if (docc.registroActualizacion() && docc.registroReporteEspecifico()) {

                        objsesion.setAttribute("ok", "Se actualizo el documento");
                        response.sendRedirect("anexos.jsp");

                    } else {
                        objsesion.setAttribute("error", "No se actualizó el documento");
                        response.sendRedirect("anexos.jsp");
                    }
                } else { //Si hay un documento actualizandose

                    fec.setFecha(doc.getFecha());
                    fec.sumaHoras();
                    doc.setFecha(fec.getFecha());

                    if (doc.actualizarDocumento()) {

                        version = Integer.toString(Integer.parseInt(doc.getVersion()) + 1);
                        documento docc = new documento(idDocumento, nomDocumento, fecha, idUsuario, version, tipoDocumento, "", "", documento);

                        if (docc.actulizarActualizacion() && docc.registroReporteEspecifico()) {
                            objsesion.setAttribute("ok", "Se actualizo el documento");
                            response.sendRedirect("anexos.jsp");
                        } else {
                            objsesion.setAttribute("error", "No se actualizó el documento");
                            response.sendRedirect("anexos.jsp");
                        }
                    } else {
                        objsesion.setAttribute("error", "No se ingreso la actualización anterior");
                        response.sendRedirect("anexos.jsp");
                    }
                }
            } else {
                objsesion.setAttribute("error", "El documento a actualizar no existe");
                response.sendRedirect("anexos.jsp");
            }
            //}
        }
        if (seleccion.equals("")) {
            objsesion.setAttribute("error", "Debe completar todos los campos");
            response.sendRedirect("anexos.jsp");
        }
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
