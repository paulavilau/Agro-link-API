package com.myproject.agrolink.requestmodel;

import java.util.Date;
import java.util.Optional;

import lombok.Data;

@Data
public class AddClientRequest {

    private String firebaseId;

    private Optional<String> firstName;

    private Optional<String> lastName;

    private String email;

    private Optional<String> phone;

    private Optional<String> companyName;

    private Optional<String> cif;

    private Optional<String> address;

    private Integer countyId;

    private Optional<String> locality;

    private Date creationDate;

}
