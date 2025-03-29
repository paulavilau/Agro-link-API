package com.myproject.agrolink.requestmodel;

import lombok.Data;

import java.util.Optional;

@Data
public class AddFarmRequest {

    private String name;

    private String firebaseId;

    private Integer countyId;

    private String locality;

    private Optional<String> address;

    private String phone;

    private String email;
}
