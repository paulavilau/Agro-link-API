package com.myproject.agrolink.controller;

import java.util.HashMap;
import java.util.Map;

import java.lang.System;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.System;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.requestmodel.StripePaymentRequest;
import com.myproject.agrolink.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

@RestController
@RequestMapping("/agrolink/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody StripePaymentRequest paymentRequest)
            throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentRequest);
        String paymentStr = paymentIntent.toJson();
        System.out.println(paymentStr);
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/refund-payment")
    public Refund refundPayment(@RequestParam String chargeId) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("chargeId", chargeId);

        return Refund.create(params);
    }

    @PostMapping("/refund")
    public ResponseEntity<?> refundPayment(@RequestBody Map<String, Object> payload) {
    String paymentIntentId = (String) payload.get("paymentIntent");
    
    try {
        Refund refund = paymentService.refundPayment(paymentIntentId);
        return new ResponseEntity<>(refund, HttpStatus.OK);
    } catch (StripeException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Failed to refund payment", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
