package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.documento;

/**
 *
 * @author Einer
 */
public class reporteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Se obtiene los datos del jsp
        String idDocumento = request.getParameter("idDocumento");
        String tipoReporte = request.getParameter("tipoReporte");

        HttpSession objsesion = request.getSession();
        documento doc = new documento();

        objsesion.removeAttribute("datos");

        doc.setIdDocumento(idDocumento);

        if (tipoReporte.equals("reporteEspecifico")) {

            if (doc.consultaReporteEspecifico()) {
                objsesion.setAttribute("datos", doc.getDatos());
                objsesion.setAttribute("ok", "Consulta Exitosa");
                response.sendRedirect("reportes.jsp");
            } else {
                objsesion.setAttribute("error", "No se encontraron resultados");
                response.sendRedirect("reportes.jsp");
            }
        }
        if (tipoReporte.equals("reporteGlobal")) {
            
            objsesion.setAttribute("error", "Por el momento solo funciona los reportes espeficico.");
            response.sendRedirect("reportes.jsp");

        }
        if (tipoReporte.equals("")) {

            objsesion.setAttribute("error", "Se debe llenar todos los campos");
            response.sendRedirect("reportes.jsp");
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
