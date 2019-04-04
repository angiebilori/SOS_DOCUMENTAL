package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consultas;

/**
 * @author Einer
 */
public class autenticarServlet extends HttpServlet {

    public static String nombre = "", apellido = "", rol = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        Consultas co = new Consultas();
        Consultas coa = new Consultas();
        
        HttpSession objetosesion = request.getSession(true);
        if (co.autenticacion(usuario, password)) {
            
            coa.actualizarAuto();
            
            if (password.equals("AbCd12345")) {

                objetosesion.setAttribute("informacionCC", "Primer inicio de sesion, debe cambiar la contraseña");
                response.sendRedirect("cambioContrasena.jsp");
            
            } else {
                Consultas coi = new Consultas();

                if (coi.datosUsuario(usuario)) {

                    //Este codigo sirve para almacenar la session o nombre de usuario
                    objetosesion.setAttribute("usuario", nombre + " " + apellido);
                    objetosesion.setAttribute("rol", rol);
                    objetosesion.setAttribute("idUsuario", usuario);
                    
                    response.sendRedirect("menu.jsp"); //Si es verdadero nos envia a el menu jsp

                } else {
                    objetosesion.setAttribute("informacionA", "No se obtubo la inforamción del usuario");
                    response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
                }
            }
        } else {
            objetosesion.setAttribute("informacionA", "Usuario y/o contraseña erroneos");
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
