package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderRequest {

    private Integer clientId;

    private String phone;

    private BigDecimal productsValue;

    private BigDecimal deliveryFee;

    private String locality;

    private String address;

    private Integer paymentMethodId;

    // private Integer paymentId;
}
