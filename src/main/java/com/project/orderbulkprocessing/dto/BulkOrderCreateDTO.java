package com.project.orderbulkprocessing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkOrderCreateDTO {
    private List<OrderCreateDTO> orders;
}
