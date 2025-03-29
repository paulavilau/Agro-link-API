package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifyOrderStatusRequest {

    private Integer orderStatusId;

    private Integer farmUserId;
    
    private Optional<Integer> status;

    private Optional<String> deliveryEstimate;
}
