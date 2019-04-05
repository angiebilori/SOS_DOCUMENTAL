package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Einer
 */
public class descargarServlet extends HttpServlet {

    public static byte[] documento = null;
    public static byte[] documentoA = null;
    public static String idDocumento = "";
    public static String version = "";
    public static String versionA = "";
    public static String ext = "";
    public static String extA = "";


    /*
    public String path;

    public void init() {
        path = getServletContext().getInitParameter("file");
    }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        HttpSession objsesion = request.getSession(false);

        String doc = request.getParameter("des");

        String error = "";

        try {
            if (doc.equals("doc")) {
                InputStream descargar = new ByteArrayInputStream(documento);

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachement; filename = " + idDocumento + "V" + version + ext);
                response.setContentLength(descargar.available());

                int tamanoInput = descargar.available();
                byte[] datosPDF = new byte[tamanoInput];
                descargar.read(datosPDF, 0, tamanoInput);

                ServletOutputStream oot = response.getOutputStream();
                oot.flush();
                oot.write(datosPDF);
                //response.getOutputStream().write(datosPDF);
                descargar.close();

            } else {
                if (doc.equals("act")) {

                    InputStream descargar = new ByteArrayInputStream(documentoA);

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachement; filename = " + idDocumento + "V" + versionA + extA);
                    response.setContentLength(descargar.available());

                    int tamanoInput = descargar.available();
                    byte[] datosPDF = new byte[tamanoInput];
                    descargar.read(datosPDF, 0, tamanoInput);

                    ServletOutputStream oot = response.getOutputStream();
                    oot.flush();
                    oot.write(datosPDF);
                    //response.getOutputStream().write(datosPDF);
                    descargar.close();
                } else {
                    objsesion.setAttribute("informacion", "Documento no descargado " + doc);
                    response.sendRedirect("consultas.jsp");
                }
            }

        } catch (Exception e) {
            objsesion.setAttribute("informacion", "Documento no error " + e + " doc");
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
