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
import model.Consultas;

/**
 *
 * @author Einer
 */
public class registrarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        int documento = Integer.parseInt(request.getParameter("documento"));
        String primNombre = request.getParameter("primNombre");
        String segNombre = request.getParameter("segNombre");
        String primApellido = request.getParameter("primApellido");
        String segApellido = request.getParameter("segApellido");
        String email = request.getParameter("correo");
        int rol = Integer.parseInt(request.getParameter("rol"));
        String password = "AbCd12345";

        Consultas co = new Consultas();

        if (co.registrar(documento, primNombre, segNombre, primApellido, segApellido, email, rol, password)) {

            boolean enviado = false;

            try {
                String correo = "soportesosdocumental@gmail.com";
                String contrasenia = "SOSdocumental%1503794";
                String para = email;
                String asunto = "Notificación de Registro";
                String cuerpo = "Buen día: " + primNombre + " " + primApellido + "\n \nLe informamos que se realizó el registro en: S.O.S Documental.\n \nUsuario: " + documento + "\nContraseña: " + password + "\n \nRecuerde que en su primer inicio de sesión se solicitará cambiar su contraseña.\nCordialmente,";

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
                request.setAttribute("label", "Registro Exitoso");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            } else {
                request.setAttribute("label", "Se realizó el registro, pero no se envió el correo");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("label", "No se logró realizar el registro " + rol);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
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
