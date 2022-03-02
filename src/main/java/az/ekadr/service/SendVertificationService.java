package az.ekadr.service;

import az.ekadr.properties.PropertiesHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendVertificationService {

    class MailSender {
        public boolean send(String to, String subject, String msg) {

            Properties properties = PropertiesHelper.getProperties();
            // Recipient's email ID needs to be mentioned.

            // Sender's email ID needs to be mentioned
            String from = properties.getProperty("email.user");

            // Assuming you are sending email from through gmails smtp
            String host = properties.getProperty("email.host");

            // Get system properties
            Properties prop = System.getProperties();

            // Setup mail server
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", properties.getProperty("email.port"));
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.debug", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "false");
            prop.put("mail.smtp.socketFactory.port", properties.getProperty("email.port"));



            // Get the Session object.// and pass username and password
            Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(from, properties.getProperty("email.password"));

                }

            });

            // Used to debug SMTP issues
            session.setDebug(true);

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject(subject);

                // Now set the actual message
                message.setText(msg);

                // Send message
                Transport.send(message);
                return true;
            } catch (MessagingException mex) {
                mex.printStackTrace();
                return false;
            }
        }

    }
    public boolean sendVertificationMail(String to,String subject,int code){
        try{
            MailSender mailSender = new MailSender();
            return mailSender.send(to,subject,"Your vertification code: " + code);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
