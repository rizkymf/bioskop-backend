package org.binaracademy.bioskopbackend.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String FROM_EMAIL = "rmf.morning@gmail.com";
    private static final String PASSWORD_EMAIL = "cEPQx6WaUrH4s15J";

    @Override
    public void sendEmail(String subject, String msg, String recipientEmail) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp-relay.brevo.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.socketFactory.port", "587");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL,PASSWORD_EMAIL);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}
    }
}
