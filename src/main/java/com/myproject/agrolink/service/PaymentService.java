package com.myproject.agrolink.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.myproject.agrolink.requestmodel.StripePaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

@Service
@Transactional
public class PaymentService {

    @Autowired
    public PaymentService(@Value("${stripe.key.secret}") String secretKey) {
        Stripe.apiKey = secretKey;
    }
    // @Value is an annotation in Spring Framework used for injecting values
    // from external sources, such as properties files or environment variables,
    // into Spring-managed beans.

    // Primeste informatiile de plata de la front-end
    // (doar valoare de platit, moneda si metodele de plata)
    // si returneaza un obiect de tip PaymentIntent pe care front-endul o sa il
    // trimita la Stripe pentru verificare

    public PaymentIntent createPaymentIntent(StripePaymentRequest stripePaymentRequest) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", stripePaymentRequest.getAmount());
        params.put("currency", stripePaymentRequest.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public Refund refundPayment(String paymentIntentId) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("limit", 1);
        chargeParams.put("payment_intent", paymentIntentId);
        ChargeCollection charges = Charge.list(chargeParams);
        String chargeId = charges.getData().get(0).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("charge", chargeId);

        return Refund.create(params);
    }

    

}
