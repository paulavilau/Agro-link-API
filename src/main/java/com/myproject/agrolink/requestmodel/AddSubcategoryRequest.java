package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class AddSubcategoryRequest {

    private Integer categoryId;

    private String name;

    private String imageLink;

}
