package com.glb.controllers;
 
import static com.glb.helpers.Helpers.isNullOrEmpty;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * A class for sending e-mail messages
 * @author Kevin Young
 *
 */
public class EmailUtility {
    public static void sendEmail(String host, String port,
            final String emailFrom, final String password, String emailTo,
            String subject, String message) throws AddressException,
            MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        if (isNullOrEmpty(host)) {
           host = "smtp.gmail.com";
        }
        if (isNullOrEmpty(port)) {
           port = "587";
        }
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session); 
        msg.setFrom(new InternetAddress(emailFrom));
        InternetAddress[] toAddresses = {new InternetAddress(emailTo)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
    }
}