package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Einer
 */
public class correo {

    public boolean enviarCorreo(String email, String asuntoMensaje, String cuerpoMensaje) throws UnknownHostException {

        //Optener la ip local
        InetAddress address = InetAddress.getLocalHost();

        //Url de acceso
        String url = "\n\nUrl de acceso: http://" + address.getHostAddress() + ":8080/autenticacion.jsp "
                + "\n\nCordialmente,";

        try {
            //Correo de donde saldrán los mensajes
            String correo = "soportesosdocumental@gmail.com";
            String contrasenia = "SOSdocumental%1503794";
            //Donde se enviarán los mensajes
            String para = email;

            cuerpoMensaje = cuerpoMensaje + url;

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
            message.setSubject(asuntoMensaje);
            message.setText(cuerpoMensaje);

            Transport transport = sesion.getTransport("smtp");
            transport.connect("smtp.gmail.com", correo, contrasenia);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (MessagingException e) {
            System.out.println("Error enviarCorreo: " + e);
            return false;
        }
    }
}
