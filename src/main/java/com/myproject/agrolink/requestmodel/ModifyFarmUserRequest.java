package com.myproject.agrolink.requestmodel;

import java.util.Optional;

import lombok.Data;

@Data
public class ModifyFarmUserRequest {

    private Integer id;

    private Optional<String> name;

    private Optional<Integer> role;

    private Optional<String> email;

    private Optional<Integer> validated;
}
