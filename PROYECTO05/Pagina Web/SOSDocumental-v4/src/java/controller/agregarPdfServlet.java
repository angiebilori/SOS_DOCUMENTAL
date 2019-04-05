/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Consultas;

/**
 *
 * @author Einer
 */
@WebServlet(name = "agregarPdfServlet", urlPatterns = {"/agregarPdf"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class agregarPdfServlet extends HttpServlet {

    public static String error = "";
    //public static int idDocumentoo = 0, idUsuarioo = 0, versionn = 5;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
        String nomDocumento = request.getParameter("nomDocumento");
        String opcion = request.getParameter("opcion");
        String ext = request.getParameter("ext");

        int version = 1;
        int descarga = 0;
        int busqueda = 0;

        HttpSession objetosesion = request.getSession(false); //Obtener la sesion iniciada"false"
        String idUsuario = (String) objetosesion.getAttribute("idUsuario");//Obtenermos los datos del objeto

        //Para ingresar la fecha y hora de la sesion
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //Formato de fecha y hora
        String fechaRegistro = hourdateFormat.format(date);

        Consultas co = new Consultas();
        Consultas coE = new Consultas();
        Consultas coRE = new Consultas();
        Consultas coRG = new Consultas();

        InputStream inputStream = null;

        if (opcion.equals("nuevoRegistro")) {

            if (coE.exixtenciaDocumento(Integer.toString(idDocumento))) {

                objetosesion.setAttribute("informacionE", "El documento a ingresar ya existe");
                response.sendRedirect("anexos.jsp");

            } else {
                if (request.getPart("anexo").getSize() > 0) {
                    Part filePart = request.getPart("anexo");

                    if (filePart.getSize() > 0) {

                        inputStream = filePart.getInputStream();

                        if (co.registroDocumento(idDocumento, nomDocumento, fechaRegistro, idUsuario, inputStream, version, ext)) {

                            if ((coRG.registroReporteGlobal(idDocumento, descarga, busqueda, fechaRegistro, idUsuario)) && (coRE.registroReporteEspecifico(idDocumento, nomDocumento, fechaRegistro, idUsuario, version))) {
                                objetosesion.setAttribute("informacionV", "Se guardo el documento");
                                response.sendRedirect("anexos.jsp");
                            } else {
                                objetosesion.setAttribute("informacionV", "Se guardo el documento NO se guardo Rporte GLobal ni Reporte Expecifico");
                                response.sendRedirect("anexos.jsp");
                            }
                        } else {
                            objetosesion.setAttribute("informacionE", "No se guardó documento 0 " + ext);
                            response.sendRedirect("anexos.jsp");
                        }
                    } else {
                        objetosesion.setAttribute("informacionE", "No cargo el documento");
                        response.sendRedirect("anexos.jsp");
                    }
                } else {
                    objetosesion.setAttribute("informacionE", "No cargo el documento");
                    response.sendRedirect("anexos.jsp");
                }
            }
        }

        if (opcion.equals("actualizar")) {

            if (coE.exixtenciaDocumento(Integer.toString(idDocumento))) {

                if (request.getPart("anexo").getSize() > 0) {
                    Part filePart = request.getPart("anexo");

                    if (filePart.getSize() > 0) {

                        inputStream = filePart.getInputStream();

                        if (co.actualizarDocumento(idDocumento, fechaRegistro, idUsuario, inputStream)) {
                            objetosesion.setAttribute("informacionV", "Se actualizo el documento");
                            response.sendRedirect("anexos.jsp");

                        } else {
                            objetosesion.setAttribute("informacionE", "No se actualizó documento");
                            response.sendRedirect("anexos.jsp");
                        }
                    } else {
                        objetosesion.setAttribute("informacionE", "No se cargo el documento");
                        response.sendRedirect("anexos.jsp");
                    }
                } else {
                    objetosesion.setAttribute("informacionE", "No se cargo el documento");
                    response.sendRedirect("anexos.jsp");
                }
            } else {
                objetosesion.setAttribute("informacionE", "El documento a actualizar no existe");
                response.sendRedirect("anexos.jsp");
            }
        }

        if (opcion.equals("")) {
            objetosesion.setAttribute("informacionE", "Debe completar todos los campos");
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
