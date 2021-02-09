package com.project.orderbulkprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateDTO {
    private String itemId;
    private  int quantity;
}
