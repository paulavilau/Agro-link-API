package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifyCategoryRequest {

    private Integer id;

    private Optional<String> name;

    private Optional<String> imageLink;
}
