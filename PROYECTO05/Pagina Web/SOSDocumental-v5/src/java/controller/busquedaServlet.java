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
public class busquedaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession objsesion = request.getSession(); //Obtener la sesion iniciada"false"

        //Se obtiene los datos del jsp y de la sesion
        String idNom = request.getParameter("txtIdNom");
        String idUsuario = (String) objsesion.getAttribute("idUsuario");

        //Se remueven los datos de busquedas anteriores
        objsesion.removeAttribute("datos");
        objsesion.removeAttribute("datosA");

        //Se crea el objeto de las clases necesarias
        documento doc = new documento();
        fecha fec = new fecha();

        fec.obtenerFecha();

        //Se envia el dato de busqueda a la clase documento metodo de búsqueda
        doc.setIdDocumento(idNom);

        //Consultar si el documento existe
        if (doc.consultaDocumento()) {

            //Se almacena los datos obtenidos en la sesion
            objsesion.setAttribute("datos", doc.getDatos());

            doc.setFecha(fec.getFecha());
            doc.setIdUsuario(idUsuario);
            doc.registrarBusqueda();

            //Se actualiza la tabla reporte global busquedas
            doc.consultaReporteGlobal();
            doc.setCantBusquedas(Integer.toString(Integer.parseInt(doc.getCantBusquedas()) + 1));
            doc.actualizarReporteGlobalBudquedas();

            doc.setFecha("");
            if (doc.consultaActualizacion()) {

                objsesion.setAttribute("datosA", doc.getDatosA());

                objsesion.setAttribute("ok", "Consulta Exitosa. ");
                response.sendRedirect("consultas.jsp");

            } else {
                //objsesion.setAttribute("datosA", null);
                objsesion.setAttribute("ok", "Consulta Exitosa");
                response.sendRedirect("consultas.jsp");
            }
        } else {
            objsesion.setAttribute("error", "Documento no Existe");
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
