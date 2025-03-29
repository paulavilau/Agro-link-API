package com.myproject.agrolink.requestmodel;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

@Data
public class FundsDistribRequest {

    private Integer orderStatusId;

    private Optional<BigDecimal> clientSum;

    private Optional<BigDecimal> farmSum;

    private Integer adminId;

}
