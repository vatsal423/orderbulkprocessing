package com.project.orderbulkprocessing.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentCreateDTO {
    private String paymentTypeId;
    private BigDecimal amount;
    private String cardNo;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;
}
