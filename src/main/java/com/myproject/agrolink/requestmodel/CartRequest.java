package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class CartRequest {

    private Integer clientId;

    private Optional<BigDecimal> value;
    
}
