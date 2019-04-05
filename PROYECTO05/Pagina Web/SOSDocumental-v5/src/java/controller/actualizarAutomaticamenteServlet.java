/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.documento;
import model.fecha;

/**
 *
 * @author Einer
 */
public class actualizarAutomaticamenteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession objsesion = request.getSession();

        //Creacion de objetos
        documento doc = new documento();
        fecha fec = new fecha();

        String fecha;

        fec.obtenerFecha();
        fec.restarMes();

        fecha = fec.getFecha();
        doc.setFecha(fecha);

        while (doc.consultaActualizacion()) {

            //Si hay un documento actualizandose
            fec.setFecha(doc.getFecha());
            fec.sumaHoras();
            doc.setFecha(fec.getFecha());

            if (doc.actualizarDocumento() && doc.eliminarActualizacion()) {

                objsesion.setAttribute("ok", "Se actualizo el documento");
            }
            fec.obtenerFecha();
            fec.restarMes();
            fecha = fec.getFecha();
            doc.setFecha(fecha);
        }
        response.sendRedirect("menu.jsp"); //Si es verdadero nos envia a el menu jsp

        /*
        while (doc.consultaDocumento()){
            
        }*/
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
