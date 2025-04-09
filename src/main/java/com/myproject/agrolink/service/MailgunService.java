package com.myproject.agrolink.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MailgunService {

    @Value("${mailgun.api.key}")
    private String mailgunApiKey;

    @Value("${mailgun.domain}")
    private String mailgunDomain;

    private final RestTemplate restTemplate;

    public MailgunService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        String url = "https://api.mailgun.net/v3/" + mailgunDomain + "/messages";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("api", mailgunApiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("from", "AgroLink <noreply@" + mailgunDomain + ">");
        body.add("to", to);
        body.add("subject", subject);
        body.add("text", text);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println("Mailgun response: " + response.getBody());
    }
}
