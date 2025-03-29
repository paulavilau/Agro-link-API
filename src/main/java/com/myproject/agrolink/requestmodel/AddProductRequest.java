package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class AddProductRequest {

    private String code;

    private String name;

    private Integer farmUserId;

    private Optional<String> description;

    private String unit;

    private Optional<String> measure;

    private BigDecimal price;

    private Integer subcategoryId;

    private Integer farmId;

    private Optional<Integer> inStock;

}
