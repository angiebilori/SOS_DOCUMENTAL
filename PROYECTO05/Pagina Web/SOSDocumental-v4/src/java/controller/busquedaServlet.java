package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consultas;

/**
 *
 * @author Einer
 */
public class busquedaServlet extends HttpServlet {

    public static ArrayList<String> datos = new ArrayList<String>();
    public static ArrayList<String> datosA = new ArrayList<String>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession objetosesion = request.getSession(false); //Obtener la sesion iniciada"false"

        String idNom = request.getParameter("txtIdNom");

        objetosesion.removeAttribute("datos");
        objetosesion.removeAttribute("datosA");

        datos.clear();
        datosA.clear();

        Consultas co = new Consultas();
        Consultas coi = new Consultas();

        if (co.BusquedaActualizacion(idNom)) {

            if (coi.busquedaDocumento(idNom)) {

                objetosesion.setAttribute("datos", datos);
                objetosesion.setAttribute("datosA", datosA);

                objetosesion.setAttribute("informacion", "Consulta Exitosa ");
                response.sendRedirect("consultas.jsp");

            } else {
                objetosesion.setAttribute("informacion", "Documento no Existe");
                response.sendRedirect("consultas.jsp");
            }
        } else if (coi.busquedaDocumento(idNom)) {

            objetosesion.setAttribute("datos", datos);

            objetosesion.setAttribute("informacion", "Consulta Exitosa ");
            response.sendRedirect("consultas.jsp");

        } else {
            objetosesion.setAttribute("informacion", "Documento no Existe");
            response.sendRedirect("consultas.jsp");
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
