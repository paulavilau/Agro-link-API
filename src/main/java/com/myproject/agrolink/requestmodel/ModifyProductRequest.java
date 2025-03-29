package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class ModifyProductRequest {

    private Integer productId;

    private Optional<String> code;

    private Optional<String> name;

    private Integer farmUserId;

    private Optional<String> description;

    private Optional<String> unit;

    private Optional<String> measure;

    private Optional<BigDecimal> price;

    private Optional<Integer> subcategoryId;

    private Optional<Integer> inStock;

}
