package controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Manual;
import model.dao.DAOManual;

/**
 * @author Einer
 */
@WebServlet(name = "agregarManualServlet", urlPatterns = {"/agregarManual"})
@MultipartConfig(maxFileSize = 100177215)    // maximo tamaño del archivo 100MB

public class agregarManualServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String opcion = request.getParameter("opcion"); //Se obtiene el documento

        HttpSession objsesion = request.getSession();

        Manual manu = new Manual();
        DAOManual dManu = new DAOManual();

        Part filePart = request.getPart("manual"); //Se obtiene el documento

        if (filePart.getSize() > 0) {

            manu.setDocumento(filePart.getInputStream());

            if (opcion == null) {
                if (dManu.listarManual(manu) != null) {
                    if (dManu.actualizarManual(manu)) {
                        objsesion.setAttribute("error", "Se actualizó el manual");
                        response.sendRedirect("agregarManual.jsp");
                    } else {
                        objsesion.setAttribute("error", "No se actualizó el manual");
                        response.sendRedirect("agregarManual.jsp");
                    }
                } else {
                    if (dManu.agregarManual(manu)) {
                        objsesion.setAttribute("error", "Se agrego el manual");
                        response.sendRedirect("agregarManual.jsp");
                    } else {
                        objsesion.setAttribute("error", "No se agrego el manual");
                        response.sendRedirect("agregarManual.jsp");
                    }
                }
            }
        } else {
            objsesion.setAttribute("error", "No se adjunto documento");
            response.sendRedirect("agregarManual.jsp");
        }

        if (opcion.equals("descargar")) {

            if (dManu.listarManual(manu) != null) {

                try (InputStream documento = dManu.listarManual(manu).get(0).getDocumento()) {
                    //InputStream descargar = new ByteArrayInputStream(documentoA);

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachement; filename = ManualSOSDocumental.pdf");

                    response.setContentLength(documento.available());
                    int tamanoInput = documento.available();
                    byte[] datosPDF = new byte[tamanoInput];
                    documento.read(datosPDF, 0, tamanoInput);
                    ServletOutputStream oot = response.getOutputStream();
                    oot.flush();
                    oot.write(datosPDF);
                    System.out.println("Desccargar");
                    //response.getOutputStream().write(datosPDF);
                }
            } else {
                objsesion.setAttribute("error", "No se puede descargar el documento");
                response.sendRedirect("anexos.jsp");
            }
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