package com.myproject.agrolink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.requestmodel.EmailRequest;
import com.myproject.agrolink.service.MailgunService;


import org.springframework.mail.MailException;

@RestController
@RequestMapping("/agrolink/emails")
public class EmailController {

  private final MailgunService mailgunService;

  public EmailController(MailgunService mailgunService) {
      this.mailgunService = mailgunService;
  }

  @PostMapping("/test")
  public ResponseEntity<String> sendTestEmail() {
      mailgunService.sendSimpleEmail(
          "vilaupaula@yahoo.com",
          "Salut!",
          "Acesta este un email trimis cu Mailgun și Java 🚀"
      );
      return ResponseEntity.ok("Email trimis!");
  }
}
