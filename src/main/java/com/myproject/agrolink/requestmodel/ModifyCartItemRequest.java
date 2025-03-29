package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ModifyCartItemRequest {
    private Integer cartItemId;

    private BigDecimal quantity;
}
