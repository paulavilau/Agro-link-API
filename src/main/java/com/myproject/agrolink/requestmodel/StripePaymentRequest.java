package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class StripePaymentRequest {

    private int amount;

    private String currency;

}
