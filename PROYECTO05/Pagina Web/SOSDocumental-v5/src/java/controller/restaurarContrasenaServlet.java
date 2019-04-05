package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.usuario;
import model.correo;

/**
 * @author Einer
 */
public class restaurarContrasenaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        usuario usu = new usuario();
        correo enviar = new correo();

        String idUsuario = request.getParameter("cedula");
        String email = request.getParameter("email");
        String nuevContrasena = request.getParameter("contrasena");
        String verificarContrasena = request.getParameter("verificacion");
        String cambioContrasena = request.getParameter("cambioContrasena");

        usu.setIdUsuario(idUsuario);
        usu.setEmail(email);

        HttpSession objsesion = request.getSession(true);//Para almacenar los datos

        if (cambioContrasena == null) {

            objsesion.setAttribute("cambioContrasena", "restaurar");

            if (nuevContrasena.equals(verificarContrasena) && nuevContrasena.length() > 9) {

                if (usu.autenticarRecuperacionContrasenia()) {

                    usu.setContrasenia(nuevContrasena);

                    if (usu.modificarContrasena() && usu.datosUsuario()) {

                        objsesion.removeAttribute("cambioContrasena");
                        String asuntoMensaje = "Notificación Cambio de Contraseña ";
                        String cuerpoMensaje = "Buen día: " + usu.getPrimNombre() + " " + usu.getPrimApellido()
                                + ",\n\nLe informamos que el cambio de contraseña en: S.O.S Documental, se realizó con éxito";

                        if (enviar.enviarCorreo(usu.getEmail(), asuntoMensaje, cuerpoMensaje)) {
                            objsesion.setAttribute("ok", "Se restauró la contraseña");
                            response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp

                        } else {
                            objsesion.setAttribute("ok", "Se restauró la contraseña, no se envio información");
                            response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
                        }
                    } else {
                        objsesion.setAttribute("error", "No se logró restaurar la contraseña");
                        response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
                    }
                } else {
                    objsesion.setAttribute("error", "Cedula y/o correo erroneo");
                    response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
                }
            } else {
                objsesion.setAttribute("error", "Las contraseñas no coinciden y/o es menor a nueve digitos");
                response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
            }
        } else {
            if (cambioContrasena.equals("restaurar")) {
                objsesion.setAttribute("cambioContrasena", "cambiar");
                response.sendRedirect("restaurarContrasena.jsp");
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
