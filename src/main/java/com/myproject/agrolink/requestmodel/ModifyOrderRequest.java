package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifyOrderRequest {
    private Integer orderId;

    // private Integer adminId;

    private Optional<Integer> completed;
}
