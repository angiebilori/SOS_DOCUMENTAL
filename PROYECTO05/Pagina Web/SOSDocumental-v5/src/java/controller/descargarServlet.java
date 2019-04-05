package controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.documento;
import model.fecha;

/**
 * @author Einer
 */
public class descargarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        HttpSession objsesion = request.getSession(false);

        documento doc = new documento();
        fecha fec = new fecha();

        String des = request.getParameter("des");
        String idDocumento = request.getParameter("idDocumento");
        String idUsuario = (String) objsesion.getAttribute("idUsuario");
        String fecha;

        fec.obtenerFecha();
        fecha = fec.getFecha();

        doc.setIdDocumento(idDocumento);

        if (des.equals("doc")) {

            if (doc.consultaDocumento()) {

                doc.setIdUsuario(idUsuario);
                doc.setFecha(fecha);
                doc.registrarDescarga();

                //Se actualiza la tabla reporte global descargas
                doc.consultaReporteGlobal();
                doc.setCantDescargas(Integer.toString(Integer.parseInt(doc.getCantDescargas()) + 1));
                doc.actualizarReporteGlobalDescargas();

                try (InputStream documento = doc.getDocumento()) {
                    //InputStream descargar = new ByteArrayInputStream(documentoA); //Docuemnto en tipo bytes[]

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachement; filename = " + doc.getIdDocumento() + "-V" + doc.getVersion() + doc.getTipoDocumento());
                    response.setContentLength(documento.available());
                    int tamanoInput = documento.available();
                    byte[] datosPDF = new byte[tamanoInput];
                    documento.read(datosPDF, 0, tamanoInput);
                    ServletOutputStream oot = response.getOutputStream();
                    oot.flush();
                    oot.write(datosPDF);
                    //response.getOutputStream().write(datosPDF);
                }
            } else {
                objsesion.setAttribute("error", "Documento no descargado ");
                response.sendRedirect("consultas.jsp");
            }

        } else if (des.equals("act")) {

            if (doc.consultaActualizacion()) {

                doc.setIdUsuario(idUsuario);
                doc.setFecha(fecha);
                doc.registrarDescarga();

                //Se actualiza la tabla reporte global descargas
                doc.consultaReporteGlobal();
                doc.setCantDescargas(Integer.toString(Integer.parseInt(doc.getCantDescargas()) + 1));
                doc.actualizarReporteGlobalDescargas();

                try (InputStream documento = doc.getDocumento()) {
                    //InputStream descargar = new ByteArrayInputStream(documentoA);

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachement; filename = " + doc.getIdDocumento() + "-V" + doc.getVersion() + doc.getTipoDocumento());
                    response.setContentLength(documento.available());
                    int tamanoInput = documento.available();
                    byte[] datosPDF = new byte[tamanoInput];
                    documento.read(datosPDF, 0, tamanoInput);
                    ServletOutputStream oot = response.getOutputStream();
                    oot.flush();
                    oot.write(datosPDF);
                    //response.getOutputStream().write(datosPDF);
                }
            } else {
                objsesion.setAttribute("error", "Documento no descargado ");
                response.sendRedirect("consultas.jsp");
            }
        } else {
            objsesion.setAttribute("error", "Documento no descargado ");
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
