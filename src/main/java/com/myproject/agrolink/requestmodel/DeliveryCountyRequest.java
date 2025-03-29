package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class DeliveryCountyRequest {
    private Integer countyId;

    private Integer farmId;
}
