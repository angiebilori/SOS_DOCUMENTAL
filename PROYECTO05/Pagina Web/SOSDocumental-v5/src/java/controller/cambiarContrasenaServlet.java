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
public class cambiarContrasenaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        usuario usu = new usuario();

        usu.setIdUsuario(request.getParameter("usuario"));
        usu.setContrasenia(request.getParameter("password"));

        HttpSession objsesion = request.getSession(true);

        String nuevoPassword = request.getParameter("nuevoPassword");
        String verificarPassword = request.getParameter("verificarPassword");

        correo enviarCorreo = new correo();

        if (nuevoPassword.equals(verificarPassword) && verificarPassword.length() > 9) {

            if (usu.autenticacionUsuario()) {

                usu.setContrasenia(request.getParameter("nuevoPassword"));

                if (usu.modificarContrasena() && usu.datosUsuario()) {

                    String asuntoMensaje = "Notificación Cambio de Contraseña ";
                    String cuerpoMensaje = "Buen día: " + usu.getPrimNombre() + " " + usu.getPrimApellido()
                            + ",\n\nLe informamos que se realizó el cambio de contraseña en: S.O.S Documental.\n"
                            + "\n\nCordialmente,\n \n";

                    if (enviarCorreo.enviarCorreo(usu.getEmail(), asuntoMensaje, cuerpoMensaje)) {

                        objsesion.setAttribute("ok", "El cambio de contraseña se realizó con éxito ");
                        response.sendRedirect("cambioContrasena.jsp");
                    } else {
                        System.out.println("error");
                        objsesion.setAttribute("ok", "Se cambio la contaseña, no se envió información");
                        response.sendRedirect("cambioContrasena.jsp");
                    }
                } else {
                    objsesion.setAttribute("error", "No se logró realizar el cambio de contraseña");
                    response.sendRedirect("cambioContrasena.jsp");
                }
            } else {
                objsesion.setAttribute("error", "Usuario y/o contraseña erroneo");
                response.sendRedirect("cambioContrasena.jsp");
            }
        } else {
            objsesion.setAttribute("error", "La nueva contraseña no concide y/o es menor a 9 digitos");
            response.sendRedirect("cambioContrasena.jsp");
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
