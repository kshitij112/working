package com.example.demo1.service;



import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public void sendOTP(String email, String otp) {
        // Configure your SMTP server details
        String host = "smtp.example.com";
        String username = "your_username";
        String password = "your_password";
        int port = 587;

        // Create properties and set SMTP server details
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("OTP for Registration");
            message.setText("Your OTP is: " + otp);

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception or log error
        }
    }
}
