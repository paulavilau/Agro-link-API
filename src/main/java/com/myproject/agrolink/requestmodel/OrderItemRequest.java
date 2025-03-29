package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemRequest {
    
    private Integer orderId;

    private Integer productId;

    private BigDecimal price;

    private BigDecimal quantity;
}
