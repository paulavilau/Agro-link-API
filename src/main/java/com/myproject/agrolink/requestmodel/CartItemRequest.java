package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemRequest {

    public Integer cartItemId;
    
    public Integer cartId;

    public Integer productId;

    public BigDecimal quantity;
}
