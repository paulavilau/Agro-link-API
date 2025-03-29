package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderStatusRequest {

    private Integer orderId;

    private Integer farmId;

    private BigDecimal orderSubtotal;

    private BigDecimal deliveryFee;

}
