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

        int usuario = Integer.parseInt(request.getParameter("usuario"));
        String password = request.getParameter("password");

        Consultas co = new Consultas();

        if (co.autenticacion(usuario, password)) {
            if (password.equals("AbCd12345")) {
                request.setAttribute("label", "Debe cambiar la contraseña");
                request.getRequestDispatcher("autenticacion.jsp").forward(request, response);

            } else {

                Consultas coi = new Consultas();

                if (coi.datosUsuario(usuario)) {
                    //Este codigo sirve para almacenar la session o nombre de usuario
                    HttpSession objetosesion = request.getSession(true);
                    objetosesion.setAttribute("usuario", nombre + " " + apellido);
                    objetosesion.setAttribute("rol", rol);
                    response.sendRedirect("menu.jsp"); //Si es verdadero nos envia a el menu jsp

                } else {
                    request.setAttribute("label", "No se logro obtener la informacion del usuario");
                    request.getRequestDispatcher("autenticacion.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("label", "Usuario o contraseña invalidos");
            request.getRequestDispatcher("autenticacion.jsp").forward(request, response);
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
