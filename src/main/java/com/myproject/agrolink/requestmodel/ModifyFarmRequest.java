package com.myproject.agrolink.requestmodel;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class ModifyFarmRequest {

    private Integer id;

    private Optional<String> name;

    private Optional<Integer> countyId;

    private Optional<String> locality;

    private Optional<String> address;

    private Optional<String> image;

    private Optional<String> email;

    private Optional<String> description;

    private Optional<String> phone;

    private Optional<Integer> validated;

    private Optional<BigDecimal> deliveryPrice;

}