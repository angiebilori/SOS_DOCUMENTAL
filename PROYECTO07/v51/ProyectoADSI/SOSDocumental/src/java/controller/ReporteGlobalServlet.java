package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.Fecha;
import model.ReporteGlobal;
import model.dao.DAOReporteGlobal;

/**
 *
 * @author Einer
 */
public class ReporteGlobalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ReporteGlobal reGlob = new ReporteGlobal();
    DAOReporteGlobal dReGlob = new DAOReporteGlobal();
    Fecha fec = new Fecha();

    private ArrayList<ReporteGlobal> listar;
    private String error;
    private String accion;
    private String linkRedireccionar;
    private String idDocumento;
    private String fechaIni;
    private String fechaFin;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //linkRedireccionar = request.getParameter("modulo");//El link del modulo se encuentra en la sesion
        linkRedireccionar = "ReporteGlobal.jsp";
        accion = request.getParameter("accion");

        idDocumento = request.getParameter("idDocumentoRG");
        fechaIni = request.getParameter("fechaIniRG");
        fechaFin = request.getParameter("fechaFinRG");

        reGlob.setIdDocumento(idDocumento);

        try {
            switch (accion) {

                case "Consultar":
                    if (listarREFechaId()) {
                        request.setAttribute("idDocumentoRG", idDocumento);
                        request.setAttribute("fechaIniRG", fechaIni);
                        request.setAttribute("fechaFinRG", fechaFin);
                        request.setAttribute("listarRG", listar);
                    } else {
                        request.setAttribute("errorRG", error);
                    }
                    redireccionar(request, response);
                    break;

                case "Generar":
                    if (!reporteEspecifico(response)) {
                        request.setAttribute("errorRG", error);
                        redireccionar(request, response);
                    }
                    break;
            }
        } catch (IOException | ServletException | NullPointerException e) {
            response.sendRedirect("MenuUsuario?accion=cerrarSesion"); //Si es verdadero nos envia a el menu jsp
        }
    }

    //Metodos
    protected boolean listarREFechaId() {
        listar = dReGlob.listarRGFechaIdDocumento(idDocumento, fechaIni, fec.sumarDia(fechaFin));
        if (listar != null) {
            return true;
        }
        error = "No se encontraron resultados";
        return false;
    }

    protected boolean reporteEspecifico(HttpServletResponse response) {
        if (listarREFechaId()) {
            generarReporte(response);
        } else {
            error = "No se encontraron datos";
        }
        return false;
    }

    protected void generarReporte(HttpServletResponse response) {
        try {

            //response.setContentType("application/vnd.ms-excel");//Para que abra la ventana de guardar 
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachement; filename = Reporte_Global.xls");

            // WorkbookSettings conf = new WorkbookSettings();
            //conf.setEncoding("ISO-8859-1");
            WritableWorkbook workBook = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sheet = workBook.createSheet("ReporteGlobal", 0);

            WritableFont t = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);//Estilo titulo
            WritableFont c = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);//Estilo contenido

            WritableCellFormat cFormat = new WritableCellFormat(c);
            WritableCellFormat tFormat = new WritableCellFormat(t);

            sheet.addCell(new jxl.write.Label(0, 0, "Id Reporte Global", tFormat));
            sheet.addCell(new jxl.write.Label(1, 0, "Id Documento", tFormat));
            sheet.addCell(new jxl.write.Label(2, 0, "Cantidad Descargas", tFormat));
            sheet.addCell(new jxl.write.Label(3, 0, "Cantidad Búsquedad", tFormat));
            sheet.addCell(new jxl.write.Label(4, 0, "Fecha Registro Documento", tFormat));

            for (int i = 0; i < listar.size(); i++) {
                sheet.addCell(new jxl.write.Label(0, i + 1, listar.get(i).getIdReporteGlobal(), cFormat));
                sheet.addCell(new jxl.write.Label(1, i + 1, listar.get(i).getIdDocumento(), cFormat));
                sheet.addCell(new jxl.write.Label(2, i + 1, listar.get(i).getCantidadDescargas(), cFormat));
                sheet.addCell(new jxl.write.Label(3, i + 1, listar.get(i).getCantidadBusquedas(), cFormat));
                sheet.addCell(new jxl.write.Label(4, i + 1, listar.get(i).getFechaReporteGlobal(), cFormat));
            }

            workBook.write();
            workBook.close();
            response.getOutputStream().close();

        } catch (IOException | WriteException e) {
            error = "No se descargo el documento";
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
