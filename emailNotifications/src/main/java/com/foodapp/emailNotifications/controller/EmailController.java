package com.foodapp.emailNotifications.controller;


import com.foodapp.emailNotifications.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/sendOrderConfirmation/{userId}")
    public ResponseEntity<String> sendOrderConfirmation(@PathVariable int userId) {
        sendEmailService.sendOrderConfirmationEmail(userId);
        return ResponseEntity.ok("Order confirmation email sent successfully to user " + userId);
    }
}

