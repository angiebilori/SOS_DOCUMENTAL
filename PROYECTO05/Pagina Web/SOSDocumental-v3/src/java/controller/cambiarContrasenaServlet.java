package controller;

import static controller.restaurarContrasenaServlet.apellido;
import static controller.restaurarContrasenaServlet.nombre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consultas;

/**
 *
 * @author Einer
 */
public class cambiarContrasenaServlet extends HttpServlet {

    public static String nombre = "", apellido = "", email = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nuevoPassword = request.getParameter("nuevoPassword");
        String verificarPassword = request.getParameter("verificarPassword");

        HttpSession objetosesion = request.getSession(true);

        if (nuevoPassword.equals(verificarPassword) && verificarPassword.length() > 9) {

            Consultas co = new Consultas();
            if (co.autenticacion(usuario, password)) {

                Consultas coc = new Consultas();
                if (coc.restaurarContrasena(usuario, verificarPassword)) {

                    Consultas coi = new Consultas();
                    if (coi.datosUsuario(usuario)) {

                        boolean enviado = false;
                        try {
                            String correo = "soportesosdocumental@gmail.com";
                            String contrasenia = "SOSdocumental%1503794";
                            String para = email;
                            String asunto = "Notificación de Cambio Contraseña";
                            String cuerpo = "Buen día: " + nombre + " " + apellido + ",\n \nLe informamos que el cambio de contraseña se realizó con éxito.\n \nCordialmente,";

                            Properties props = System.getProperties();

                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.user", correo);
                            props.put("mail.smtp.password", contrasenia);
                            props.put("mail.smtp.port", "587");
                            props.put("mail.smtp.auth", "true");//Autenticacion

                            Session sesion = Session.getDefaultInstance(props);
                            MimeMessage message = new MimeMessage(sesion);

                            message.setFrom(new InternetAddress(correo));
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
                            message.setSubject(asunto);
                            message.setText(cuerpo);

                            Transport transport = sesion.getTransport("smtp");
                            transport.connect("smtp.gmail.com", correo, contrasenia);
                            transport.sendMessage(message, message.getAllRecipients());
                            transport.close();
                            enviado = true;

                        } catch (Exception e) { 
                        }
                        objetosesion.setAttribute("informacionCCi", "El cambio de contraseña se realizó con éxito ");
                       
                        response.sendRedirect("cambioContrasena.jsp");
                    } else {
                        objetosesion.setAttribute("informacionCCo", "Se cambio la contaseña, no se envió información");
                    }
                } else {
                    objetosesion.setAttribute("informacionCCo", "No se logró realizar el cambio de contraseña");
                    response.sendRedirect("cambioContraseña.jsp");
                }
            } else {
                objetosesion.setAttribute("informacionCCo", "Usuario y/o contraseña erroneo");
                response.sendRedirect("cambioContrasena.jsp");
            }
        } else {
            objetosesion.setAttribute("informacionCCo", "La nueva contraseña no concide y/o es menor a 9 digitos");
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
