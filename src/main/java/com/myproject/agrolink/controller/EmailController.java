package com.myproject.agrolink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.requestmodel.EmailRequest;
import com.myproject.agrolink.service.EmailService;


import org.springframework.mail.MailException;

@RestController
@RequestMapping("/agrolink/emails")
public class EmailController {


  private EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/sendEmail")
  public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) throws MailException {
    emailService.sendEmail(emailRequest);

    return new ResponseEntity<>(HttpStatus.OK);

  }
}
