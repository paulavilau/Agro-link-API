package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class AddFarmUserRequest {

    private String firebaseId;

    private String name;

    private Integer role;

    private String email;

    private Integer farmId;
}
