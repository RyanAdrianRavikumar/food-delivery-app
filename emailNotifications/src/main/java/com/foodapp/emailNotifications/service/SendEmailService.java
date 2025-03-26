package com.foodapp.emailNotifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.mail.username}")
    private String fromEmailId;

    private final String USER_SERVICE_URL = "http://localhost:8080/users/";

    public void sendEmail(String recipient, String body, String subject) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmailId);
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email sent successfully to " + recipient);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendOrderConfirmationEmail(int userId) {
        try {
            String emailUrl = USER_SERVICE_URL + userId + "/email";
            String userEmail = restTemplate.getForObject(emailUrl, String.class);

            if (userEmail != null && !userEmail.isEmpty()) {
                String subject = "Order Confirmation";
                String body = "Thank you for placing an order! Your order is being processed.";

                sendEmail(userEmail, body, subject);
            } else {
                System.out.println("Failed to get user's email.");
            }
        } catch (Exception e) {
            System.err.println("Error while fetching user email or sending confirmation email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
