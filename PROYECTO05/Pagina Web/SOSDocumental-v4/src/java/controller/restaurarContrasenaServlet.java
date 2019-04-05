package controller;

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
 * @author Einer
 */
public class restaurarContrasenaServlet extends HttpServlet {

    public static String nombre = "", apellido = "", email = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String cedula = request.getParameter("cedula");
        String email = request.getParameter("email");
        String nuevContrasena = request.getParameter("contrasena");
        String verificarContrasena = request.getParameter("verificacion");

        Consultas co = new Consultas();
        HttpSession objetosesion = request.getSession(true);//Para almacenar los datos

        if (nuevContrasena.equals(verificarContrasena)&& nuevContrasena.length()>9) {

            if (co.autenticarCambioContrasena(cedula, email)) {

                Consultas coo = new Consultas();
                if (coo.restaurarContrasena(cedula, nuevContrasena)) {

                    Consultas coi = new Consultas();
                    if (coi.datosUsuario(cedula)) {

                        boolean enviado = false;
                        try {
                            String correo = "soportesosdocumental@gmail.com";
                            String contrasenia = "SOSdocumental%1503794";
                            String para = email;
                            String asunto = "Notificación de Cambio Contraseña";
                            String cuerpo = "Buen día: " + nombre + " " + apellido + "\n \nLe informamos que la recuperacion de la contraseña se realizó con éxito.\nCordialmente,";

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
                            out.print("<h1>Error: " + e + "</>");
                        }
                        if (enviado) {
                            objetosesion.setAttribute("informacionC", "La contraseña se restauró con éxito");
                            response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
                        }
                    } else {
                        objetosesion.setAttribute("informacionC", "Se restauró la contraseña, no se envio información");
                        response.sendRedirect("autenticacion.jsp"); //Si es verdadero nos envia a el menu jsp
                    }
                } else {
                    objetosesion.setAttribute("informacionC", "No se logró restaurar la contraseña");
                    response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
                }
            } else {
                objetosesion.setAttribute("informacionC", "Cedula y/o correo erroneo");
                response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
            }
        } else {
            objetosesion.setAttribute("informacionC", "Las contraseñas no coinciden y/o es menor a nueve digitos");
            response.sendRedirect("restaurarContrasena.jsp"); //Si es verdadero nos envia a el menu jsp
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
