package controller;

import model.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Einer
 */
public class autenticarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        usuario usu = new usuario();
        HttpSession objsesion = request.getSession(true);

        String idUsuaario = request.getParameter("idUsuario");
        String contrasenia = request.getParameter("contrasenia");

        usu.setIdUsuario(idUsuaario);
        usu.setContrasenia(contrasenia);

        if (usu.autenticacionUsuario()) {

            //coa.actualizarAuto();
            if (usu.getContrasenia().equals("AbCd12345")) {

                objsesion.setAttribute("cambioContrasena", "Primer inicio de sesión, debe cambiar la contraseña");
                response.sendRedirect("cambioContrasena.jsp");

            } else if (usu.datosUsuario()) {

                //Este codigo sirve para almacenar la session o nombre de usuario
                objsesion.setAttribute("usuario", usu.getPrimNombre() + " " + usu.getPrimApellido());
                objsesion.setAttribute("rol", usu.getRol());
                objsesion.setAttribute("idUsuario", usu.getIdUsuario());

                response.sendRedirect("actualizarAutomaticamente");

            } else {
                objsesion.setAttribute("error", "No se obtubo la información del usuario");
                response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
            }
        } else {
            objsesion.setAttribute("error", "Usuario y/o contraseña erroneos");
            response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
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
