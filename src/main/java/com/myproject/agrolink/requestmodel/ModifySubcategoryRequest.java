package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifySubcategoryRequest {

    private Integer id;

    private Optional<String> name;

    private Optional<String> imageLink;
}
