package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Einer
 */
public class enviarCorreoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        boolean enviado = false;

        try {

            String identificacion = request.getParameter("identificacion");

            String correo = "soportesosdocumental@gmail.com";
            String contrasenia = "SOSdocumental%1503794";
            String para = "ablopez22@misena.edu.co";
            String asunto = "Correo de Prueba";
            String cuerpo = "Correo enviado\nA traves de un servlet";

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
            //transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            enviado = true;

        } catch (Exception e) {
            out.print("<h1>Error: " + e + "</>");
        }
        if (enviado) {
            out.print("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal ('Mensaje fue enviado !', '', 'succes');");
            out.print("});");
            out.print("</script>");

            RequestDispatcher er = request.getRequestDispatcher("autenticacion.jsp");
            er.include(request, response);
        } else {
            out.print("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal ('Mensaje no enviado !', '', 'error');");
            out.print("});");
            out.print("</script>");
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
