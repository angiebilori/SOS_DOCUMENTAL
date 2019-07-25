package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Einer
 */
public class Correo {

    public static String plantillaCorreo(String cuerpoMensaje, String nombreUsuario) throws Throwable {
        String Pagina = ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
                + " \r\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
                + " \r\n"
                + " <head>\r\n"
                + " \r\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
                + " \r\n"
                + "<title>Correo SOS Documental</title>\r\n"
                + " \r\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n"
                + " \r\n"
                + "</head>\r\n"
                + " \r\n"
                + "<body style=\"margin: 0; padding: 0;\">\r\n"
                + " \r\n"
                + " <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
                + " \r\n"
                + "<tr>\r\n"
                + " \r\n"
                + " <td>\r\n"
                + " \r\n"
                + "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td align=\"center\"  style=\"padding: 5px 0 2px 0; background:url('https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/itCjTBE/blue-abstract-moving-flowing-waves-on-white-background-blurred-smooth-design-video-animation-ultra-hd-4k-3840x2160_rffdhgnp_thumbnail-full05.png') no-repeat bottom right;\" width=\"100%\" >"
                + " \r\n"
                + "<img src=\"logo/LOGO.gif\" alt=\"SOS Documental\" width=\"400\" height=\"80\" style=\"display: block;\" />"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td bgcolor=\"#ffffff\">\r\n"
                + " \r\n"
                + "<table style=\"background:url('https://ak2.picdn.net/shutterstock/videos/16130842/thumb/1.jpg')  no-repeat bottom right; background-size: cover;\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"background-image: linear-gradient(rgb(214, 159, 93) 0%, rgb(255, 251, 126) 20%, rgb(242, 216, 153) 50%, rgb(255, 255, 255) 60%, rgb(247, 205, 101) 70%, rgb(169, 111, 42) 100%);\">\r\n"
                + " \r\n"
                + " <font size=\"4\" face=\"ITC Edwardian Script\" color=\"black\"><i>Estimado " + nombreUsuario + ",</i></font>\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"padding: 20px 0 30px 0; \">\r\n"
                + " \r\n"
                + "<font size=\"4\"><i>"
                + " Cordial Saludo,<br><br>\n"
                + cuerpoMensaje  /*+" <b>"  
                + "</i></font> \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td>\r\n"
                + " \r\n"
                + "<br>"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + "</table>"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"background-image: linear-gradient(rgb(214, 159, 93) 0%, rgb(255, 251, 126) 20%, rgb(242, 216, 153) 50%, rgb(255, 255, 255) 60%, rgb(247, 205, 101) 70%, rgb(169, 111, 42) 100%);\">\r\n"
                + " \r\n"
                + " <font size=\"4\" color=\"black\"><i>Cordialmente, </i></font><br>\r\n"
                + " <font size=\"4\" color=\"black\"><b><i>SOS DOCUMENTAL </i></b></font><br>\r\n"
                
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + "</table>"
                + " \r\n"
                + "</body>"
                + " \r\n"
                + "</html>");
        return Pagina;
    }

    public static String plantillaCorreo(String cuerpoMensaje, String nombreUsuario, String codigo, String usuario) throws Throwable {
        String Pagina = ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
                + " \r\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
                + " \r\n"
                + " <head>\r\n"
                + " \r\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
                + " \r\n"
                + "<title>Correo SOS Documental</title>\r\n"
                + " \r\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n"
                + " \r\n"
                + "</head>\r\n"
                + " \r\n"
                + "<body style=\"margin: 0; padding: 0;\">\r\n"
                + " \r\n"
                + " <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
                + " \r\n"
                + "<tr>\r\n"
                + " \r\n"
                + " <td>\r\n"
                + " \r\n"
                + "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td align=\"center\"  style=\"padding: 5px 0 2px 0; background:url('https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/itCjTBE/blue-abstract-moving-flowing-waves-on-white-background-blurred-smooth-design-video-animation-ultra-hd-4k-3840x2160_rffdhgnp_thumbnail-full05.png') no-repeat bottom right;\" width=\"100%\" >"
                + " \r\n"
                + "<img src=\"logo/LOGO.gif\" alt=\"SOS Documental\" width=\"400\" height=\"80\" style=\"display: block;\" />"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td bgcolor=\"#ffffff\">\r\n"
                + " \r\n"
                + "<table style=\"background:url('https://ak2.picdn.net/shutterstock/videos/16130842/thumb/1.jpg')  no-repeat bottom right; background-size: cover;\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"background-image: linear-gradient(rgb(214, 159, 93) 0%, rgb(255, 251, 126) 20%, rgb(242, 216, 153) 50%, rgb(255, 255, 255) 60%, rgb(247, 205, 101) 70%, rgb(169, 111, 42) 100%);\">\r\n"
                + " \r\n"
                + " <font size=\"4\" face=\"ITC Edwardian Script\" color=\"black\"><i>Estimado " + nombreUsuario + ",</i></font>\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"padding: 20px 0 30px 0; \">\r\n"
                + " \r\n"
                + "<font size=\"4\"><i>"
                + " Cordial Saludo,<br><br>\n"
                + cuerpoMensaje /*+ " <b>" 
		+ "</i></font> \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td>\r\n"
                + " \r\n"
                + "<br>"
                + "<br>"
                + "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"70%\">\r\n"
                + " \r\n"
                + " <tr style=\"background-image: linear-gradient(rgb(0, 104, 184) 0%, rgb(49, 147, 222) 20%, rgb(175, 217, 250) 42%, rgb(255, 255, 255) 52%, rgb(164, 214, 252) 63%, rgb(49, 147, 222) 82%, rgb(0, 104, 184) 100%);\">"
                + " \r\n"
  
                + "<td align=\"center\" width=\"260\" valign=\"top\">"
                + " \r\n"
          
                + "<font color=\"black\">" + "<i><b>Fecha de Cambio</b></i>" + "</font>" + "\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + "<td align=\"center\" width=\"260\" valign=\"top\">\r\n"
                + " \r\n"
                
                + "<font color=\"black\">" + "<i><b>Usuario</b></i>" + "</font>" + "\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + "<td align=\"center\" width=\"260\" valign=\"top\">\r\n"
                + " \r\n"
                + " "
                + "<font color=\"black\">" + "<i><b>Codigo</b></i>" + "</font>" + "\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td align=\"center\" width=\"260\" valign=\"top\">\r\n"
                + " Aqui va la fecha" + "\r\n"
                + "</td>\r\n"
                + " \r\n"
                + "<td align=\"center\" width=\"260\" valign=\"top\">\r\n"
                + " \r\n"
                + " " + usuario + "\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + "<td align=\"center\" width=\"260\" valign=\"top\">\r\n"
                + " \r\n"
                + " " + codigo + "\r\n"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + "</table>"
                + "<br>"
                + "<br>"
                + "<br>"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + "</table>"
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + " <tr>\r\n"
                + " \r\n"
                + "<td style=\"background-image: linear-gradient(rgb(214, 159, 93) 0%, rgb(255, 251, 126) 20%, rgb(242, 216, 153) 50%, rgb(255, 255, 255) 60%, rgb(247, 205, 101) 70%, rgb(169, 111, 42) 100%);\">\r\n"
                + " \r\n"
                + " <font size=\"4\" color=\"black\"><i>Cordialmente, </i></font><br>\r\n"
                + " <font size=\"4\" color=\"black\"><b><i>SOS DOCUMENTAL </i></b></font><br>\r\n"
                
                + " \r\n"
                + "</td>\r\n"
                + " \r\n"
                + " </tr>\r\n"
                + " \r\n"
                + "</table>"
                + " \r\n"
                + "</body>"
                + " \r\n"
                + "</html>");
        return Pagina;
    }

    public boolean enviarCorreo(String email, String asuntoMensaje, String cuerpoMensaje, String nombreUsuario) throws UnknownHostException, Throwable {

        //Optener la ip local
        InetAddress address = InetAddress.getLocalHost();

        //Url de acceso
        String url = "\n\nUrl de acceso: http://" + address.getHostAddress() + ":8081/AutenticarUsuario.jsp "
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

            BodyPart text = new MimeBodyPart();
            text.setContent(plantillaCorreo(cuerpoMensaje, nombreUsuario), "text/html");
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(text);
            MimeMessage message = new MimeMessage(sesion);

            message.setFrom(new InternetAddress(correo));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asuntoMensaje);
            message.setContent(multiPart);

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

    public boolean enviarCorreo(String email, String asuntoMensaje, String cuerpoMensaje, String nombreUsuario, String codigo, String usuario) throws UnknownHostException, Throwable {

        //Optener la ip local
        InetAddress address = InetAddress.getLocalHost();

        //Url de acceso
        String url = "\n\nUrl de acceso: http://" + address.getHostAddress() + ":8081/AutenticarUsuario.jsp "
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

            BodyPart text = new MimeBodyPart();
            text.setContent(plantillaCorreo(cuerpoMensaje, nombreUsuario, codigo, usuario), "text/html");
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(text);
            MimeMessage message = new MimeMessage(sesion);

            message.setFrom(new InternetAddress(correo));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asuntoMensaje);
            message.setContent(multiPart);

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
     public boolean enviarCorreo(String email, String asuntoMensaje, String cuerpoMensaje) throws UnknownHostException {

        //Optener la ip local
        InetAddress address = InetAddress.getLocalHost();

        //Url de acceso
        String url = "\n\nUrl de acceso: http://" + address.getHostAddress() + ":8080/AutenticarUsuario.jsp "
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
