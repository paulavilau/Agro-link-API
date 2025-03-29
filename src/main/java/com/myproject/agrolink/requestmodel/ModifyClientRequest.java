package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifyClientRequest {

    private Integer id;

    private Optional<String> firstName;

    private Optional<String> lastName;

    private Optional<String> email;

    private Optional<String> phone;

    private Optional<String> companyName;

    private Optional<String> cif;

    private Optional<String> address;

    private Optional<Integer> countyId;

    private Optional<String> locality;

    private Optional<Integer> active;
}
