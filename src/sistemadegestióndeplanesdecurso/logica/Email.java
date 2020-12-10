/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.logica;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author angel
 */
public class Email {
    public static void enviarEmail(String usuario, String contraseña, String destinatario){
        String remitente = "correo@correo";
        String contraseñaRemitente = "contraseña";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", remitente);
        properties.put("mail.password", contraseñaRemitente);

        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(remitente, "Universidad Veracruzana"));

            InternetAddress[] internetAddresses = {new InternetAddress(destinatario)};
            mimeMessage.setRecipients(Message.RecipientType.TO,internetAddresses);
            mimeMessage.setSubject("Sistema de Gestión de Practicas Profesionales");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Usuario:" + usuario + "\nContraseña:" + contraseña);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            mimeMessage.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(remitente, contraseñaRemitente);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
