package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
    static private Properties mailServerProperties;
    static private Session getMailSession;
    static private MimeMessage generateMailMessage;


    public static void generateAndSendEmail(String[] to, String[] cc, String subject, String emailBody) throws AddressException, MessagingException {

        // Configurando las propiedades del servidor de correo
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Creando la sesión y rellenando información
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        for(String destinatario : to)
        	generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        for(String conCopia : cc)
        	generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(conCopia));
        generateMailMessage.setSubject(subject); //ASUNTO
        generateMailMessage.setContent(emailBody, "text/html");

        // Añadiendo el protocolo
        Transport transport = getMailSession.getTransport("smtp");

        // Conexión y envío de mensaje.
        transport.connect("smtp.gmail.com", "testing.grupoonetec", "-Onetec2019");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
    
}
