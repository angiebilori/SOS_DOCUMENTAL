/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
 * @author PARKA
 */
@WebServlet(name = "agregarPdfServlet", urlPatterns = {"/agregarPdf"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class agregarPdfServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));
        String nomDocumento = request.getParameter("nomDocumento");
        String version = "Versión " + request.getParameter("version");

        HttpSession objsesion = request.getSession(false); //Obtener la sesion iniciada"false"
        int idUsuario = (int) objsesion.getAttribute("idUsuario");//Obtenermos los datos del objeto

        //Para ingresar la fecha y hora de la sesion
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //Formato de fecha y hora
        String fechaRegistro = hourdateFormat.format(date);

        InputStream inputStream = null;

        if (request.getPart("anexo").getSize() > 0) {
            Part filePart = request.getPart("anexo");

            if (filePart.getSize() > 0) {

                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                inputStream = filePart.getInputStream();

                Consultas co = new Consultas();

                if (co.registrarPdf(idDocumento, nomDocumento, fechaRegistro, idUsuario, inputStream, version)) {
                    out.print("<h1>Documento guardado</>");
                } else {
                    out.print("<h1>Error: 1</>");
                }
            } else {
                out.print("<h1>Error:2 </>");
            }
        } else {
            out.print("<h1>Error:3 </>");
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
