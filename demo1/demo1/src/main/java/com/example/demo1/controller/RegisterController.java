package com.example.demo1.controller;




import com.example.demo1.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@RestController
public class RegisterController {
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String otp = generateOTP(); // Generate OTP

        // Send OTP via email or SMS
        sendOTP(user.getEmail(), otp); // Assuming the user's email is stored in the User object

        // Save the OTP in the User object or in the database for later verification
        user.setOtp(otp);

        // Save the user to the database using UserRepository

        return "Registration successful";
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOTP(String email, String otp) {
        // Implement the logic to send OTP to the provided email
        // You can use a library like JavaMail API or an external email service
        // Here's an example using JavaMail API:
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
