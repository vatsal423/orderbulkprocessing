package com.project.orderbulkprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateDTO {
    private String orderId;
    private String orderStatusId;
}
