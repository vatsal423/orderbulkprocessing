package com.project.orderbulkprocessing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDTO {

    private String customerId;
    private String shippingId;
    private List<ItemCreateDTO> items;
    private List<PaymentCreateDTO> payments;
}