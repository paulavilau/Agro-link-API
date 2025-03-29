package com.myproject.agrolink.requestmodel;

import lombok.Data;

@Data
public class ImageRequest {

    private String link;

    private Integer productId;

    private Integer farmUserId;
}
