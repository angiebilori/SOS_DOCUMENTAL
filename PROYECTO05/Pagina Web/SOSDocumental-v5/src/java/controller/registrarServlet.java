package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.correo;
import model.usuario;

/**
 *
 * @author Einer
 */
public class registrarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        usuario usu = new usuario();
        correo enviar = new correo();

        usu.setIdUsuario(request.getParameter("documento"));
        usu.setPrimNombre(request.getParameter("primNombre"));
        usu.setSegNombre(request.getParameter("segNombre"));
        usu.setPrimApellido(request.getParameter("primApellido"));
        usu.setSegApellido(request.getParameter("segApellido"));
        usu.setEmail(request.getParameter("correo"));
        usu.setIdRol(request.getParameter("rol"));
        usu.setContrasenia("AbCd12345");

        HttpSession objsesion = request.getSession(true);

        if (usu.datosUsuario()) {

            objsesion.setAttribute("error", "Usuario ya existe");
            response.sendRedirect("registro.jsp");

        } else {
            if (usu.registroUsuario()) {

                String asuntoMensaje = "Notificación de Registro";
                String cuerpoMensaje = "Buen día: " + usu.getPrimNombre() + " " + usu.getPrimApellido()
                        + ",\n \nLe informamos que se realizó el registro en: S.O.S Documental.\n \nUsuario: "
                        + usu.getIdUsuario() + "\nContraseña: " + usu.getContrasenia()
                        + "\n \nRecuerde que en su primer inicio de sesión se solicitará cambiar su contraseña.";

                if (enviar.enviarCorreo(usu.getEmail(), asuntoMensaje, cuerpoMensaje)) {

                    objsesion.setAttribute("ok", "Se realizó el registro");
                    response.sendRedirect("registro.jsp");

                } else {

                    objsesion.setAttribute("error", "Se realizó el registro, NO se envió la información ");
                    response.sendRedirect("registro.jsp");
                }

            } else {

                objsesion.setAttribute("error", "No se logró realizar el registro ");
                response.sendRedirect("registro.jsp");
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
