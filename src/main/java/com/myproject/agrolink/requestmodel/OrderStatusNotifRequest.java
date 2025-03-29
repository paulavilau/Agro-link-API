package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class OrderStatusNotifRequest {

    private Integer orderStatusId;

    private Integer status;

    private String email;

    private String message;
}
